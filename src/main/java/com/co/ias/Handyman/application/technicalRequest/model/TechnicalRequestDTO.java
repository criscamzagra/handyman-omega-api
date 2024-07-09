package com.co.ias.Handyman.application.technicalRequest.model;

import com.co.ias.Handyman.application.request.domain.RequestId;
import com.co.ias.Handyman.application.technical.domain.TechnicalId;

import com.co.ias.Handyman.application.technicalRequest.domain.EndDay;
import com.co.ias.Handyman.application.technicalRequest.domain.StartDay;
import com.co.ias.Handyman.application.technicalRequest.domain.TechnicalRequest;
import com.co.ias.Handyman.application.technicalRequest.domain.TechnicalRequestId;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;



public class TechnicalRequestDTO {

    private Integer technicalRequestId;

    private Integer requestId;

    public Integer technicalId;

    private LocalDateTime startDay;

    private  LocalDateTime endDay;


    public TechnicalRequestDTO(){

    }

    public TechnicalRequestDTO(Integer technicalRequestId, Integer requestId, Integer technicalId, LocalDateTime startDay, LocalDateTime endDay) {
        this.technicalRequestId = technicalRequestId;
        this.requestId = requestId;
        this.technicalId = technicalId;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public static TechnicalRequestDTO fromResultSet(ResultSet resultSet) throws SQLException {
        TechnicalRequestDTO technicalRequestDTO = new TechnicalRequestDTO();
        technicalRequestDTO.setTechnicalRequestId(resultSet.getInt("id_technical_request"));
        technicalRequestDTO.setRequestId(resultSet.getInt("id_request"));
        technicalRequestDTO.setTechnicalId(resultSet.getInt("id_technical"));
        technicalRequestDTO.setStartDay(resultSet.getTimestamp("start_day").toLocalDateTime() );
        technicalRequestDTO.setEndDay(resultSet.getTimestamp("end_day").toLocalDateTime());

        return technicalRequestDTO;
    }

    public TechnicalRequest toDomain(Integer technicalRequestId, Integer technicalId, Integer requestId, LocalDateTime endDay, LocalDateTime day){
        return  new TechnicalRequest(
          new TechnicalRequestId(this.technicalRequestId),
                new RequestId(this.requestId),
                new TechnicalId(this.technicalId),
                new StartDay(startDay),
                new EndDay(this.endDay)
        );
    }

    public static TechnicalRequestDTO fromDomain(TechnicalRequest technicalRequest){
        return new TechnicalRequestDTO  (
                technicalRequest.getTechnicalRequestId().getValue(),
                technicalRequest.getRequestId().getValue(),
                technicalRequest.getTechnicalId().getValue(),
                technicalRequest.getStartDay().getValue(),
                technicalRequest.getEndDay().getValue()
        );
    }


    /*
     Getters and Setters
    */

    public Integer getTechnicalRequestId() {
        return technicalRequestId;
    }

    public void setTechnicalRequestId(Integer technicalRequestId) {
        this.technicalRequestId = technicalRequestId;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getTechnicalId() {
        return technicalId;
    }

    public void setTechnicalId(Integer technicalId) {
        this.technicalId = technicalId;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDateTime startDay) {
        this.startDay = startDay;
    }

    public LocalDateTime getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDateTime endDay) {
        this.endDay = endDay;
    }


}
