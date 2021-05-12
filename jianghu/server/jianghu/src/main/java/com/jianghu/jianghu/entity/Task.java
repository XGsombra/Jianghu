package com.jianghu.jianghu.entity;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

  private String taskId;
  private String title;
  private Date date;
  private String content;
  private Integer commission;
  private String publisherId;
  private String takerId;
  private Boolean active;
  private Double locationLatitude;
  private Double locationLongitude;

  public Task(String taskId, String title, Date date, String content, Integer commission,
      String publisherId, String takerId, Boolean active, Double locationLatitude,
      Double locationLongitude) {
    this.taskId = taskId;
    this.title = title;
    this.date = date;
    this.content = content;
    this.commission = commission;
    this.publisherId = publisherId;
    this.takerId = takerId;
    this.active = active;
    this.locationLatitude = locationLatitude;
    this.locationLongitude = locationLongitude;
  }

  public Task(String taskId, String title, String content, Integer commission, String publisherId,
      Double locationLatitude, Double locationLongitude) {
    this.taskId = taskId;
    this.title = title;
    this.date = new Date();
    this.content = content;
    this.commission = commission;
    this.publisherId = publisherId;
    this.takerId = "";
    this.active = true;
    this.locationLatitude = locationLatitude;
    this.locationLongitude = locationLongitude;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getCommission() {
    return commission;
  }

  public void setCommission(Integer commission) {
    this.commission = commission;
  }

  public String getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(String publisherId) {
    this.publisherId = publisherId;
  }

  public String getTakerId() {
    return takerId;
  }

  public void setTakerId(String takerId) {
    this.takerId = takerId;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Double getLocationLatitude() {
    return locationLatitude;
  }

  public void setLocationLatitude(Double locationLatitude) {
    this.locationLatitude = locationLatitude;
  }

  public Double getLocationLongitude() {
    return locationLongitude;
  }

  public void setLocationLongitude(Double locationLongitude) {
    this.locationLongitude = locationLongitude;
  }
}
