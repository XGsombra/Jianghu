package com.jianghu.jianghu.controller;

import com.jianghu.jianghu.dto.AddTaskInputDto;
import com.jianghu.jianghu.dto.HttpExceptionOutputDto;
import com.jianghu.jianghu.entity.Task;
import com.jianghu.jianghu.serviceImpl.TaskServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  /** Deactivate a task.
   *
   * @param taskId the id of the task
   * @param request the http request
   * @return the http status
   */
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

  /** Get the available tasks at certain page.
   *
   * @param page the page number
   * @param request the http request
   * @return the available tasks at the page
   */
  @GetMapping("/")
  public ResponseEntity<?> getAvailableTasks(@RequestParam Integer page,
                                             HttpServletRequest request) {
    if (page < 0) {
      return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
    }
    List<Task> tasks = taskServiceImpl.getAvailableTasks(page);
    return new ResponseEntity<>(tasks, HttpStatus.valueOf(200));
  }

  /** Get the tasks by related userId.
   *
   * @param userId the userId of related user
   * @param role the role of the user, "taker" or "publisher"
   * @param page the number of page
   * @param request the http request
   * @return the tasks
   */
  @GetMapping("/{userId}")
  public ResponseEntity<?> getTasksByUserId(@PathVariable String userId,
                                            @RequestParam String role,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            HttpServletRequest request) {

    if ((!role.equals("taker") && !role.equals("publisher")) || page < 0){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
    }
    List<Task> tasks;
    if (role.equals("publisher")){
      tasks = taskServiceImpl.getTasksByPublisherId(userId, page);
    } else {
      tasks = taskServiceImpl.getTasksByTakerId(userId, page);
    }
    return new ResponseEntity<>(tasks, HttpStatus.valueOf(200));
  }

  /**
   * Get tasks by location range.
   *
   * @param lowerLatitude  the lower bound of latitude
   * @param upperLatitude  the upper bound of latitude
   * @param lowerLongitude the lower bound of longitude
   * @param upperLongitude the upper bound of longitude
   * @param page           the page number
   * @return the tasks
   */
  @GetMapping("/location")
  public ResponseEntity<?> getTasksByLocation(@RequestParam Double lowerLatitude,
                                              @RequestParam Double lowerLongitude,
                                              @RequestParam Double upperLatitude,
                                              @RequestParam Double upperLongitude,
                                              @RequestParam(defaultValue = "0") Integer page) {
    if (lowerLatitude == null ||
        lowerLongitude == null ||
        upperLatitude == null ||
        upperLongitude == null ||
        lowerLatitude > upperLatitude ||
        lowerLongitude > upperLongitude ||
        (page != null && page < 0) ||
        lowerLatitude < -90 ||
        lowerLongitude < -190 ||
        upperLatitude > 90 ||
        upperLongitude > 180){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
    }

    List<Task> tasks = taskServiceImpl.getTasksByLocation(lowerLatitude, upperLatitude,
        lowerLongitude, upperLongitude, page);

    return new ResponseEntity<>(tasks, HttpStatus.valueOf(200));
  }

  @GetMapping("/commission")
  public ResponseEntity<?> getTasksByCommission(@RequestParam Integer lowerCommission,
                                                @RequestParam Integer upperCommission,
                                                @RequestParam(defaultValue = "0") Integer page) {
    if (lowerCommission == null ||
        upperCommission == null ||
        (page != null && page < 0) ||
        lowerCommission > upperCommission ||
        lowerCommission < 0){
      return new ResponseEntity<>(new HttpExceptionOutputDto("Bad Input"), HttpStatus.valueOf(400));
    }

    List<Task> tasks = taskServiceImpl.getTasksByCommission(lowerCommission, upperCommission, page);

    return new ResponseEntity<>(tasks, HttpStatus.valueOf(200));
  }
}
