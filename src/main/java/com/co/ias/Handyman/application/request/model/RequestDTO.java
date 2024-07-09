package com.co.ias.Handyman.application.request.model;

import com.co.ias.Handyman.application.person.domain.PersonId;
import com.co.ias.Handyman.application.request.domain.Direction;
import com.co.ias.Handyman.application.request.domain.EstimatedDay;
import com.co.ias.Handyman.application.request.domain.Request;
import com.co.ias.Handyman.application.request.domain.RequestId;
import com.co.ias.Handyman.application.service.domain.ServiceId;

public class RequestDTO {

    private Integer requestId;
    private Integer personId;
    private Integer serviceId;
    private String direction;
    private String estimatedDay;

    /*
      No Args Constructor
    */
    public RequestDTO() {
    }

    /*
     All Args Constructor
    */

    public RequestDTO(Integer requestId, Integer personId, Integer serviceId, String direction, String estimatedDay) {
        this.requestId = requestId;
        this.personId = personId;
        this.serviceId = serviceId;
        this.direction = direction;
        this.estimatedDay = estimatedDay;
    }

    /*
     Method to domain
    */
    public Request toDomain(){
        return new Request(
                new RequestId(requestId),
                new PersonId(personId),
                new ServiceId(serviceId),
                new Direction(direction),
                new EstimatedDay(estimatedDay)
        );
    }

    /*
      Method From domain
    */
    public static RequestDTO fromDomain(Request request){
        return new RequestDTO(
                request.getRequestId().getValue(),
                request.getPersonId().getValue(),
                request.getServiceId().getValue(),
                request.getDirection().getValue(),
                request.getEstimatedDay().getValue()
        );
    }

    /*
      Getters and Setters
    */

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getEstimatedDay() {
        return estimatedDay;
    }

    public void setEstimatedDay(String estimatedDay) {
        this.estimatedDay = estimatedDay;
    }
}
