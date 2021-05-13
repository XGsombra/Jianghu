package com.jianghu.jianghu.mapper;

import com.jianghu.jianghu.entity.Task;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.relational.core.sql.In;

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

  List<Task> getAvailableTasks(Integer page, Integer itemNumPerPage);

  List<Task> getTasksByPublisherId(String publisherId, Integer page, Integer itemNumPerPage);

  List<Task> getTasksByTakerId(String takerId, Integer page, Integer itemNumPerPage);

  List<Task> getTasksByLocation(Double lowerLatitude, Double upperLatitude,
      Double lowerLongitude, Double upperLongitude, Integer page, Integer itemNumPerPage);

  List<Task> getTasksByCommission(Integer lowerCommission, Integer upperCommission, Integer page,
      Integer itemNumPerPage);
}
