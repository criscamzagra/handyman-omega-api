package com.co.ias.Handyman.application.technicalRequest.model;

import java.time.LocalDateTime;

public class DayWeekDTO {
    private  String day;
    private Integer hours;
    private String startTime;
    private String endTime;

    public DayWeekDTO(String day, Integer hours, String startTime, String endTime) {
        this.day = day;
        this.hours = hours;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayWeekDTO() {

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
