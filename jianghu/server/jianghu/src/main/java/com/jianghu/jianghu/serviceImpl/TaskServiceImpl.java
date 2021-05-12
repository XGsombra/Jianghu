package com.jianghu.jianghu.serviceImpl;

import com.jianghu.jianghu.entity.Task;
import com.jianghu.jianghu.mapper.TaskMapper;
import com.jianghu.jianghu.service.TaskService;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired
  private TaskMapper taskMapper;

  /** Initialize the task table.
   *
   * @return true if initialized the task table, false if the table already exists
   */
  @Override
  public Boolean initializeTaskTable() {
    if (!taskMapper.existTaskTable()) {
      taskMapper.createTaskTable();
      System.out.println("Initialization: Successfully initiated table task");
      return true;
    }
    System.out.println("Initialization: table task already exists");
    return false;
  }

  /**
   * check if task exists
   *
   * @param taskId the id of the task
   * @return true if the task exists
   */
  @Override
  public Boolean checkTaskExist(String taskId) {
    return taskMapper.checkTaskExist(taskId);
  }

  /**
   * Get task information by taskId.
   *
   * @param taskId the id of the task
   * @return the task object
   */
  @Override
  public Task getTaskByTaskId(String taskId) {
    return taskMapper.getTaskByTaskId(taskId);
  }

  /**
   * Add task to database.
   *
   * @param title             the title of the task
   * @param content           the content of the task
   * @param commission        the commission amount of the task, unit is cent
   * @param publisherId       the userId of publisher
   * @param locationLatitude  the latitude of task location
   * @param locationLongitude the longitude of task location
   * @return
   */
  @Override
  public void addTask(String title, String content, Integer commission, String publisherId,
      Double locationLatitude, Double locationLongitude) {
    String taskId = UUID.randomUUID().toString();
    Date date = new Date();
    taskMapper.addTask(taskId, title, date, content, commission, publisherId, "",
        true, locationLatitude, locationLongitude);
  }

  /**
   * Take the task.
   *
   * @param takerId the userId of the user who take the task
   * @param taskId  the id of the task
   */
  @Override
  public Boolean takeTask(String takerId, String taskId) {
    Task task = taskMapper.getTaskByTaskId(taskId);
    // check if the task is not taken yet
    if (task.getTakerId() == ""){
      taskMapper.takeTask(taskId, takerId);
      return true;
    }
    return false;
  }

  /**
   * Yield the task.
   *
   * @param takerId the userId of the user who yield the task
   * @param taskId  the id of the task
   */
  @Override
  public Boolean yieldTask(String takerId, String taskId) {
    Task task = taskMapper.getTaskByTaskId(taskId);
    // check if the task is taken by the user with given id.
    if (task.getTakerId() == takerId) {
      taskMapper.yieldTask(taskId);
      return true;
    }
    return false;
  }

  /**
   * @param taskId the id of the task
   * @return true if the task is successfully deactivated, false if not authorized
   */
  @Override
  public void deactivateTask(String taskId) {
  }
}
