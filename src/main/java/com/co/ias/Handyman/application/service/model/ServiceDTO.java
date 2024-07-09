package com.co.ias.Handyman.application.service.model;

import com.co.ias.Handyman.application.service.domain.Service;
import com.co.ias.Handyman.application.service.domain.ServiceId;
import com.co.ias.Handyman.application.service.domain.ServiceName;

public class ServiceDTO {
    /*
      Service DTO Attributes
    */
    private Integer serviceId;
    private String serviceName;

    /*
      No Args Constructor
    */
    public ServiceDTO(){

    }

    /*
      All Args Constructor
    */
    public ServiceDTO(Integer serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    /*
     Method to domain
    */
    public Service toDomain(){
        return new Service(
                new ServiceId(serviceId),
                new ServiceName(serviceName)
        );
    }

    /*
      Method from domain
    */
    public static ServiceDTO fromDomain(Service service){
        return new ServiceDTO(
          service.getServiceId().getValue(),
          service.getServiceName().getValue()
        );
    }

    /*
      Getters and Setters
    */
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
