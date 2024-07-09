package com.co.ias.Handyman.application.technical.ports.out;


import com.co.ias.Handyman.application.technical.domain.Technical;
import com.co.ias.Handyman.application.technical.domain.TechnicalId;
import com.co.ias.Handyman.application.technical.model.TechnicalDBO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface TechnicalRepository extends CrudRepository<TechnicalDBO, Integer> {

}
