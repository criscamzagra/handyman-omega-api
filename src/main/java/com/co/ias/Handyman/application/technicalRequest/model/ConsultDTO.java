package com.co.ias.Handyman.application.technicalRequest.model;

import org.springframework.stereotype.Service;


public class ConsultDTO {

    private Integer idTechnical;
    private Integer week;
    private Integer hoursTotal;
    private Integer hoursNormal;
    private Integer nightHoursTomorrow;
    private Integer nightHoursNight;
    private Integer sundayHours;
    private Integer hoursExtraNormal;
    private Integer nightExtraHoursTomorrow;
    private Integer nightExtraHoursNight;
    private Integer sundayExtraHours;

    public ConsultDTO(Integer idTechnical, Integer week, Integer hoursTotal, Integer hoursNormal, Integer nightHoursTomorrow, Integer nightHoursNight, Integer sundayHours, Integer hoursExtraNormal, Integer nightExtraHoursTomorrow, Integer nightExtraHoursNight, Integer sundayExtraHours) {
        this.idTechnical = idTechnical;
        this.week = week;
        this.hoursTotal = hoursTotal;
        this.hoursNormal = hoursNormal;
        this.nightHoursTomorrow = nightHoursTomorrow;
        this.nightHoursNight = nightHoursNight;
        this.sundayHours = sundayHours;
        this.hoursExtraNormal = hoursExtraNormal;
        this.nightExtraHoursTomorrow = nightExtraHoursTomorrow;
        this.nightExtraHoursNight = nightExtraHoursNight;
        this.sundayExtraHours = sundayExtraHours;
    }

    public ConsultDTO() {

    }

    public Integer getIdTechnical() {
        return idTechnical;
    }

    public void setIdTechnical(Integer idTechnical) {
        this.idTechnical = idTechnical;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getHoursTotal() {
        return hoursTotal;
    }

    public void setHoursTotal(Integer hoursTotal) {
        this.hoursTotal = hoursTotal;
    }

    public Integer getHoursNormal() {
        return hoursNormal;
    }

    public void setHoursNormal(Integer hoursNormal) {
        this.hoursNormal = hoursNormal;
    }

    public Integer getNightHoursTomorrow() {
        return nightHoursTomorrow;
    }

    public void setNightHoursTomorrow(Integer nightHoursTomorrow) {
        this.nightHoursTomorrow = nightHoursTomorrow;
    }

    public Integer getNightHoursNight() {
        return nightHoursNight;
    }

    public void setNightHoursNight(Integer nightHoursNight) {
        this.nightHoursNight = nightHoursNight;
    }

    public Integer getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(Integer sundayHours) {
        this.sundayHours = sundayHours;
    }

    public Integer getHoursExtraNormal() {
        return hoursExtraNormal;
    }

    public void setHoursExtraNormal(Integer hoursExtraNormal) {
        this.hoursExtraNormal = hoursExtraNormal;
    }

    public Integer getNightExtraHoursTomorrow() {
        return nightExtraHoursTomorrow;
    }

    public void setNightExtraHoursTomorrow(Integer nightExtraHoursTomorrow) {
        this.nightExtraHoursTomorrow = nightExtraHoursTomorrow;
    }

    public Integer getNightExtraHoursNight() {
        return nightExtraHoursNight;
    }

    public void setNightExtraHoursNight(Integer nightExtraHoursNight) {
        this.nightExtraHoursNight = nightExtraHoursNight;
    }

    public Integer getSundayExtraHours() {
        return sundayExtraHours;
    }

    public void setSundayExtraHours(Integer sundayExtraHours) {
        this.sundayExtraHours = sundayExtraHours;
    }
}
