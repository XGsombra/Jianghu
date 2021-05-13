package com.jianghu.jianghu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTaskInputDto extends InputDto{

  @JsonProperty("title")
  private String title;

  @JsonProperty("content")
  private String content;

  @JsonProperty("commission")
  private Integer commission;

  @JsonProperty("locationLatitude")
  private Double locationLatitude;

  @JsonProperty("locationLongitude")
  private Double locationLongitude;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  /**
   * Checks if the input DTO is valid
   *
   * @return true if the input is valid, false otherwise
   */
  @Override
  public Boolean check() {
    if (this.title == null ||
        this.content == null ||
        this.content.length() > 1000 ||
        this.commission == null ||
        this.commission < 0 ||
        this.locationLatitude == null ||
        this.locationLongitude == null ||
        this.locationLatitude < -90 ||
        this.locationLongitude < -180 ||
        this.locationLatitude > 90 ||
        this.locationLongitude > 180)
      return false;
    return true;
  }
}
