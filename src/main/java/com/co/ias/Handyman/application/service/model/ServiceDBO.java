package com.co.ias.Handyman.application.service.model;

import com.co.ias.Handyman.application.service.domain.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
@Table(name = "service")
public class ServiceDBO {

    @Id
    @Column(name = "id_service")
    private Integer serviceId;
    @Column(name = "names_service")
    private String serviceName;

    public ServiceDBO() {
    }

    public ServiceDBO(Integer serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    public static ServiceDBO fromResultSet(ResultSet resultSet) throws SQLException {
        ServiceDBO serviceDBO = new ServiceDBO();
        serviceDBO.setServiceId(resultSet.getInt("id_service"));
        serviceDBO.setServiceName(resultSet.getString("names_service"));

        return serviceDBO;
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
