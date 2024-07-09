package com.co.ias.Handyman.application.request.domain;

import com.co.ias.Handyman.application.person.domain.PersonId;
import com.co.ias.Handyman.application.service.domain.ServiceId;

public class Request {

    private final RequestId requestId;
    private final PersonId personId;
    private final ServiceId serviceId;
    private final Direction direction;
    private final EstimatedDay estimatedDay;

    public Request(RequestId requestId, PersonId personId, ServiceId serviceId, Direction direction, EstimatedDay estimatedDay) {
        this.requestId = requestId;
        this.personId = personId;
        this.serviceId = serviceId;
        this.direction = direction;
        this.estimatedDay = estimatedDay;
    }

    public RequestId getRequestId() {
        return requestId;
    }

    public PersonId getPersonId() {
        return personId;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public Direction getDirection() {
        return direction;
    }

    public EstimatedDay getEstimatedDay() {
        return estimatedDay;
    }
}
