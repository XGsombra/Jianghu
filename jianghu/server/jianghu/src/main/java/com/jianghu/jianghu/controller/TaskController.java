package com.jianghu.jianghu.controller;

import com.jianghu.jianghu.dto.AddTaskInputDto;
import com.jianghu.jianghu.dto.AddUserInputDto;
import com.jianghu.jianghu.dto.HttpExceptionOutputDto;
import com.jianghu.jianghu.entity.Task;
import com.jianghu.jianghu.serviceImpl.TaskServiceImpl;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  @Autowired
  private TaskServiceImpl taskServiceImpl;

  /** Add a task to the community.
   *
   * @param addTaskInputDto input information
   * @param request http request
   * @return the http status
   */
  @PostMapping("/")
  public ResponseEntity<?> addTask(
      @RequestBody AddTaskInputDto addTaskInputDto,
      HttpServletRequest request)
  {
      // check for bad input
      if (!addTaskInputDto.check()){
        return new ResponseEntity<>(
            new HttpExceptionOutputDto("Bad Input"),
            HttpStatus.valueOf(400)
        );
      }

      // the publisher is the currently logged in user
      String userId = (String) request.getSession().getAttribute("userId");
      taskServiceImpl.addTask(
          addTaskInputDto.getTitle(),
          addTaskInputDto.getContent(),
          addTaskInputDto.getCommission(),
          userId,
          addTaskInputDto.getLocationLatitude(),
          addTaskInputDto.getLocationLongitude()
      );

      return new ResponseEntity<>(HttpStatus.valueOf(200));
  }

  /** Take or yield a task.
   *
   * @param taskId the id of the task
   * @param action the action to take or yield the task
   * @param request the http request
   * @return the http status
   */
  @PatchMapping("/")
  public ResponseEntity<?> takeTask(@RequestParam String taskId,
                                    @RequestParam String action,
                                    HttpServletRequest request) {
    if (taskId == null || taskId == "" || (!action.equals("take") && !action.equals("yield"))){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
    }
    // check if the task exists
    if (!taskServiceImpl.checkTaskExist(taskId)){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Task does not exist"),
                                  HttpStatus.valueOf(404));
    }
    // get the userId of task taker
    String userId = (String) request.getSession().getAttribute("userId");
    Boolean success;
    if (action.equals("take")) {
      success = taskServiceImpl.takeTask(userId, taskId);
    } else {
      success = taskServiceImpl.yieldTask(userId, taskId);
    }

    if (success){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Operation Failed"),
                                  HttpStatus.valueOf(409));
    }
    return new ResponseEntity<>(HttpStatus.valueOf(200));
  }

  @PatchMapping("/activation")
  public ResponseEntity<?> deactivateTask(@RequestParam String taskId,
                                          HttpServletRequest request) {
    if (taskId == null || taskId == "" ){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
    }
    // check if the task exists
    if (!taskServiceImpl.checkTaskExist(taskId)){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Task does not exist"),
          HttpStatus.valueOf(404));
    }
    // get the userId of task taker
    String userId = (String) request.getSession().getAttribute("userId");
    Task task = taskServiceImpl.getTaskByTaskId(taskId);

    // only the publisher can deactivate the task
    if (!task.getPublisherId().equals(userId)){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Action not authorized"),
          HttpStatus.valueOf(401));
    }
    taskServiceImpl.deactivateTask(taskId);
    return new ResponseEntity<>(HttpStatus.valueOf(200));
  }

}
