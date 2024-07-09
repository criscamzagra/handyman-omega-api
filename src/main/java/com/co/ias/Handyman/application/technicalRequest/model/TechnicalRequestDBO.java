package com.co.ias.Handyman.application.technicalRequest.model;

import com.co.ias.Handyman.application.request.domain.RequestId;
import com.co.ias.Handyman.application.technical.domain.TechnicalId;
import com.co.ias.Handyman.application.technicalRequest.domain.EndDay;
import com.co.ias.Handyman.application.technicalRequest.domain.StartDay;
import com.co.ias.Handyman.application.technicalRequest.domain.TechnicalRequest;
import com.co.ias.Handyman.application.technicalRequest.domain.TechnicalRequestId;

import javax.persistence.*;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "technical_request")
public class TechnicalRequestDBO {


    /*
      Technical Request DTO Attributes
    */
    @Id
    @SequenceGenerator(name = "technical_request_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_technical_request")
    private Integer technicalRequestId;

    @Column(name = "id_request")
    private Integer requestId;

    @Column(name = "id_technical")
    public Integer technicalId;

    @Column(name = "start_date")
    private LocalDateTime startDay;

    @Column(name = "end_date")
    private LocalDateTime endDay;

    /*
      No Args Constructor
    */
    public TechnicalRequestDBO() {
    }

    /*
      All Args Constructor
    */
    public TechnicalRequestDBO(Integer technicalRequestId, Integer requestId, Integer technicalId, LocalDateTime startDay, LocalDateTime endDay) {
        this.technicalRequestId = technicalRequestId;
        this.requestId = requestId;
        this.technicalId = technicalId;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    /*
      Method To domain
    */
    public TechnicalRequest toDomain(){
        return new TechnicalRequest(
                new TechnicalRequestId(technicalRequestId),
                new RequestId(requestId),
                new TechnicalId(technicalId),
                new StartDay(startDay),
                new EndDay(endDay)
        );
    }

    /*
     Method From Domain
    */
    public static TechnicalRequestDBO fromDomain(TechnicalRequest technicalRequest){
        return new TechnicalRequestDBO(
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
        return this.technicalRequestId;
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
