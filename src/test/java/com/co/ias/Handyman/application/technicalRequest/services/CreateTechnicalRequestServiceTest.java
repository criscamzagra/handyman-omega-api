package com.co.ias.Handyman.application.technicalRequest.services;

import com.co.ias.Handyman.application.service.ports.out.ServiceRepository;
import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;
import com.co.ias.Handyman.shared.errors.BadRequestException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class CreateTechnicalRequestServiceTest {

    @InjectMocks
    private CreateTechnicalRequestService createTechnicalRequestService;

    @Mock
    private TechnicalRequestRepository technicalRequestRepository;
    @Mock
    private  TechnicalRepository technicalRepository;
    @Mock
    private  ServiceRepository serviceRepository;



    @Test
    public void execute() throws SQLException, FileNotFoundException, NoSuchFieldException {
        TechnicalRequestDBO technicalRequest = new TechnicalRequestDBO(1,1,2, LocalDateTime.now(),LocalDateTime.now());
        when(technicalRepository.existsById(technicalRequest.technicalId)).thenReturn(true);
        when(serviceRepository.existsById(technicalRequest.getRequestId())).thenReturn(true);
        when( technicalRequestRepository.existsTechnicalRequestDTOByStartDayAndEndDay(technicalRequest.getStartDay(), technicalRequest.getEndDay())).thenReturn(false);
        when(technicalRequestRepository.save(technicalRequest)).thenReturn(technicalRequest);
        TechnicalRequestDBO response = this.createTechnicalRequestService.execute(technicalRequest);
        assertNotNull(response);
    }

    @Test
    public void executeException()  {
        TechnicalRequestDBO technicalRequest = new TechnicalRequestDBO(1,1,2, LocalDateTime.now(),LocalDateTime.now());
        when(technicalRepository.existsById(technicalRequest.technicalId)).thenReturn(false);
        when(serviceRepository.existsById(technicalRequest.getRequestId())).thenReturn(false);

        try {
            this.createTechnicalRequestService.execute(technicalRequest);

        }catch (SQLException | FileNotFoundException | NoSuchFieldException e){
            Assert.assertThat(e.getMessage(), is("Data Not Found"));
        }

    }

    @Test
    public void getTechnicalRequestDTOException()  {
        TechnicalRequestDBO technicalRequest = new TechnicalRequestDBO(1,1,2, LocalDateTime.now(),LocalDateTime.now());
        when(technicalRepository.existsById(technicalRequest.technicalId)).thenReturn(true);
        when(serviceRepository.existsById(technicalRequest.getRequestId())).thenReturn(false);

        try {
            this.createTechnicalRequestService.execute(technicalRequest);

        }catch (SQLException | FileNotFoundException | NoSuchFieldException e){
            Assert.assertThat(e.getMessage(), is("Data Not Found"));
        }

    }

    @Test
    public void TechnicalRequestDTOException()  {
        TechnicalRequestDBO technicalRequest = new TechnicalRequestDBO(1,1,2, LocalDateTime.now(),LocalDateTime.now());
        when(technicalRepository.existsById(technicalRequest.technicalId)).thenReturn(true);
        when(serviceRepository.existsById(technicalRequest.getRequestId())).thenReturn(true);
        when( technicalRequestRepository.existsTechnicalRequestDTOByStartDayAndEndDay(technicalRequest.getStartDay(), technicalRequest.getEndDay())).thenReturn(true);

        try {
            this.createTechnicalRequestService.execute(technicalRequest);

        }catch (SQLException | FileNotFoundException | NoSuchFieldException e){
            Assert.assertThat(e.getMessage(), is("Data Not Found"));
        }

    }


}