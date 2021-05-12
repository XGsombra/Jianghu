package com.jianghu.jianghu.mapper;

import com.jianghu.jianghu.entity.Task;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {

  Boolean existTaskTable();

  void createTaskTable();

  Boolean checkTaskExist(String taskId);

  void addTask(String taskId, String title, Date date, String content, Integer commission,
      String publisherId, String takerId, Boolean active, Double locationLatitude,
      Double locationLongitude);

  void takeTask(String taskId, String takerId);

  void yieldTask(String taskId);

  void deactivateTask(String taskId);

  Task getTaskByTaskId(String taskId);

  List<Task> getAllUntakenTasks();

  List<Task> getAllTasksByPublisherId(String publisherId);

  List<Task> getAllTasksByTakerId(String takerId);

  List<Task> getAllTasksByLocation(Double lowerLatitude, Double upperLatitude,
      Double lowerLongitude, Double upperLongitude);

  List<Task> getAllTasksByCommission(Integer lowerCommission, Integer upperCommission);
}
