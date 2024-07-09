package com.co.ias.Handyman.application.service.ports.out;


import com.co.ias.Handyman.application.service.domain.Service;
import com.co.ias.Handyman.application.service.domain.ServiceId;
import com.co.ias.Handyman.application.service.model.ServiceDBO;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<ServiceDBO, Integer> {



}
