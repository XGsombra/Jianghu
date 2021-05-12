package com.jianghu.jianghu.service;

import com.jianghu.jianghu.entity.Task;

public interface TaskService {


    /** Initialize the task table.
     *
     * @return true if initialized the task table, false if the table already exists
     */
    Boolean initializeTaskTable();


    /** check if task exists.
     *
     * @param taskId the id of the task
     * @return true if the task exists
     */
    Boolean checkTaskExist(String taskId);

    /** Get task information by taskId.
     *
     * @param taskId the id of the task
     * @return the task object
     */
    Task getTaskByTaskId(String taskId);

    /** Add task to database.
     *
     * @param title the title of the task
     * @param content the content of the task
     * @param commission the commission amount of the task, unit is cent
     * @param publisherId the userId of publisher
     * @param locationLatitude the latitude of task location
     * @param locationLongitude the longitude of task location
     */
    void addTask(String title, String content, Integer commission, String publisherId,
        Double locationLatitude, Double locationLongitude);

    /** Take the task.
     *
     * @param takerId the userId of the user who take the task
     * @param taskId the id of the task
     */
    Boolean takeTask(String takerId, String taskId);

    /** Yield the task.
     *
     * @param takerId the userId of the user who yield the task
     * @param taskId the id of the task
     */
    Boolean yieldTask(String takerId, String taskId);

    /** deactivate task.
     *
     * @param taskId the id of the task
    */
    void deactivateTask(String taskId);
}
