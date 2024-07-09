package com.co.ias.Handyman.infraestructure.adapters.out;


import com.co.ias.Handyman.application.service.ports.out.ServiceRepository;
import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
public class PostgresqlTechnicalRequestRepository  {

    private final DataSource dataSource;
    private TechnicalRequestRepository technicalRequestRepository;
    private TechnicalRepository technicalRepository;
    private ServiceRepository serviceRepository;

    public PostgresqlTechnicalRequestRepository(DataSource dataSource,
                                                TechnicalRequestRepository technicalRequestRepository,
                                                TechnicalRepository technicalRepository,
                                                ServiceRepository serviceRepository) {

        this.dataSource = dataSource;
        this.technicalRequestRepository = technicalRequestRepository;
        this.technicalRepository = technicalRepository;
        this.serviceRepository = serviceRepository;
    }


    public Integer save(TechnicalRequestDBO technicalRequestDBO){



        try(Connection connection = dataSource.getConnection()) {

            TechnicalRequestDBO techRequest = technicalRequestRepository.save(technicalRequestDBO);


        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }

        return technicalRequestDBO.getRequestId();

    }

    public boolean existsById(Integer technicalId){

        try (Connection connection = dataSource.getConnection()){

            boolean existsTech = technicalRepository.existsById(technicalId);
            return existsTech;
        } catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }

    }

    public boolean existsByIdService(Integer serviceId){
        try(Connection connection = dataSource.getConnection()){

            boolean existsIdService = serviceRepository.existsById(serviceId);

            return existsIdService;
        } catch (SQLException exception){
            throw new RuntimeException("Error querying database", exception);
        }


    }

    public boolean existsTechnicalRequestDTOByStartDay(LocalDateTime startDay, LocalDateTime endDay){

        //String sql = "SELECT start_date from technical_request;";

        try (Connection connection = dataSource.getConnection()) {

            boolean existsDate = technicalRequestRepository.existsTechnicalRequestDTOByStartDayAndEndDay(startDay, endDay);


            return existsDate;


        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }



    }



}
