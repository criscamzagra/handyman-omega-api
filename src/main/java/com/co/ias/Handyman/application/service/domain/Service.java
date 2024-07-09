package com.co.ias.Handyman.application.service.domain;

public class Service {

    /*
    id_service
    names_service
     */
    private final ServiceId serviceId;
    private final ServiceName serviceName;

    public Service(ServiceId serviceId, ServiceName serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public ServiceId getServiceId() {
        return serviceId;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }
}
