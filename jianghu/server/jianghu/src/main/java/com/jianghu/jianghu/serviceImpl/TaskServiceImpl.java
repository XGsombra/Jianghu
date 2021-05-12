package com.jianghu.jianghu.serviceImpl;

import com.jianghu.jianghu.mapper.TaskMapper;
import com.jianghu.jianghu.service.TaskService;
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
}
