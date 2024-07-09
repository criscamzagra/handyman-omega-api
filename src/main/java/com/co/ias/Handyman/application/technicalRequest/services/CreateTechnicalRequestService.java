package com.co.ias.Handyman.application.technicalRequest.services;

import com.co.ias.Handyman.application.service.ports.out.ServiceRepository;
import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.domain.TechnicalRequest;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import com.co.ias.Handyman.application.technicalRequest.ports.in.CreateTechnicalRequestUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;
import org.springframework.stereotype.Service;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CreateTechnicalRequestService implements CreateTechnicalRequestUseCase {


    public static final String DATA_NOT_FOUND = "Data Not Found";
    public static final String DATA_NOT_FOUND1 = "Data Not Found";
    private final TechnicalRequestRepository technicalRequestRepository;
    private final TechnicalRepository technicalRepository;

    private final ServiceRepository serviceRepository;


    public CreateTechnicalRequestService(TechnicalRequestRepository technicalRequestRepository,
                                         TechnicalRepository technicalRepository,
                                         ServiceRepository serviceRepository) {

        this.technicalRequestRepository = technicalRequestRepository;
        this.technicalRepository = technicalRepository;
        this.serviceRepository = serviceRepository;
    }


    @Override
    public TechnicalRequestDBO execute(TechnicalRequestDBO technicalRequestDBO) throws SQLException, FileNotFoundException, NoSuchFieldException {
        TechnicalRequest technicalRequest = technicalRequestDBO.toDomain();

        boolean existsIdTech = technicalRepository.existsById(technicalRequestDBO.getTechnicalId());

        boolean existsIdService = serviceRepository.existsById(technicalRequestDBO.getRequestId());

        boolean existsDate = technicalRequestRepository.existsTechnicalRequestDTOByStartDayAndEndDay(technicalRequestDBO.getStartDay(), technicalRequestDBO.getEndDay());

        /*
         Boolean for create service after actual day
        */
        LocalDateTime now = LocalDateTime.now();
        boolean startAfterNow = technicalRequestDBO.getStartDay().isAfter(now);

        Long daysBeforeService = ChronoUnit.DAYS.between(technicalRequestDBO.getStartDay(),
                now);

        if (existsIdTech){
            return getTechnicalRequestDTO(technicalRequestDBO, existsIdService, existsDate, startAfterNow, daysBeforeService);
        } else {
            throw new SQLException(DATA_NOT_FOUND);

        }

    }

    private TechnicalRequestDBO getTechnicalRequestDTO(TechnicalRequestDBO technicalRequestDBO, boolean existsIdService, boolean existsDate, boolean startAfterNow, Long daysBeforeService) throws NoSuchFieldException, FileNotFoundException {
        if (existsIdService){
            return getTechnicalRequestDTO(technicalRequestDBO, existsDate, startAfterNow, daysBeforeService);
        } else{
            throw new FileNotFoundException(DATA_NOT_FOUND);

        }
    }

    private TechnicalRequestDBO getTechnicalRequestDTO(TechnicalRequestDBO technicalRequestDBO, boolean existsDate, boolean startAfterNow, Long daysBeforeService) throws NoSuchFieldException {
        if(!existsDate){
            return getTechnicalRequestDTO(technicalRequestDBO, startAfterNow, daysBeforeService);

        } else {
            throw new NoSuchFieldException(DATA_NOT_FOUND);
        }
    }

    private TechnicalRequestDBO getTechnicalRequestDTO(TechnicalRequestDBO technicalRequestDBO, boolean startAfterNow, Long daysBeforeService) {
        if(!startAfterNow && daysBeforeService < 7){
            technicalRequestRepository.save(technicalRequestDBO);
            return technicalRequestDBO;
        } else{
            throw new DateTimeException("Days Wrong");
        }
    }


}
