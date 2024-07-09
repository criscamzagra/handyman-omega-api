package com.co.ias.Handyman.application.technicalRequest.services;

import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.model.ConsultDTO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDTO;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;

import com.co.ias.Handyman.shared.errors.BadRequestException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConsultTechnicalRequestServiceTest {

    @InjectMocks
    private ConsultTechnicalRequestService consultTechnicalRequestService;

    @Mock
    private  TechnicalRequestRepository technicalRequestRepository;
    @Mock
    private  TechnicalRepository technicalRepository;



    List<TechnicalRequestDTO> listResult;
    ConsultDTO consultDTO;
    TechnicalRequestDTO technicalRequestDTO;

    List<TechnicalRequestDBO> consultWeek;


    public void processInformation() {
        listResult = new ArrayList<>();
        consultWeek = new ArrayList<>();
        consultDTO= new ConsultDTO( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        technicalRequestDTO =new TechnicalRequestDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now());
        listResult.add(technicalRequestDTO);
        Object[] obj = { 1, 2, 2, Timestamp.valueOf("2022-05-24 01:01:00.0"), Timestamp.valueOf("2022-05-24 22:01:00.0") };
        Object[] obj1 = { 2, 2, 2, Timestamp.valueOf("2022-05-25 01:01:00.0"), Timestamp.valueOf("2022-05-25 22:01:00.0") };
        Object[] obj2 = { 3, 2, 2, Timestamp.valueOf("2022-05-26 01:01:00.0"), Timestamp.valueOf("2022-05-26 22:01:00.0") };
        Object[] obj3 = { 4, 2, 2, Timestamp.valueOf("2022-05-25 01:01:00.0"), Timestamp.valueOf("2022-05-25 22:01:00.0") };


    }


    public void processInformationSunday() {
        listResult = new ArrayList<>();
        consultWeek = new ArrayList<>();
        consultDTO= new ConsultDTO( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        technicalRequestDTO =new TechnicalRequestDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now());
        listResult.add(technicalRequestDTO);
        Object[] obj = { 1, 2, 2, Timestamp.valueOf("2022-05-29 01:01:00.0"), Timestamp.valueOf("2022-05-29 08:01:00.0") };
        Object[] obj1 = { 2, 2, 2, Timestamp.valueOf("2022-05-28 01:01:00.0"), Timestamp.valueOf("2022-05-28 08:01:00.0") };


    }

    public void processInformationSundayExtra() {
        listResult = new ArrayList<>();
        consultWeek = new ArrayList<>();
        consultDTO= new ConsultDTO( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        technicalRequestDTO =new TechnicalRequestDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now());
        listResult.add(technicalRequestDTO);
        Object[] obj = { 1, 2, 2, Timestamp.valueOf("2022-05-26 01:01:00.0"), Timestamp.valueOf("2022-05-26 23:01:00.0") };
        Object[] obj1 = { 2, 2, 2, Timestamp.valueOf("2022-05-27 01:01:00.0"), Timestamp.valueOf("2022-05-27 23:01:00.0") };
        Object[] obj2 = { 2, 2, 2, Timestamp.valueOf("2022-05-29 01:01:00.0"), Timestamp.valueOf("2022-05-29 23:01:00.0") };


    }

    public void processInformationMorningNightHoursAndNormalAndNightHours() {
        listResult = new ArrayList<>();
        consultWeek = new ArrayList<>();
        consultDTO= new ConsultDTO( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        technicalRequestDTO =new TechnicalRequestDTO(1,1,1,LocalDateTime.now(),LocalDateTime.now());
        listResult.add(technicalRequestDTO);
        Object[] obj = { 1, 2, 2, Timestamp.valueOf("2022-05-26 01:01:00.0"), Timestamp.valueOf("2022-05-26 23:01:00.0") };
        Object[] obj1 = { 2, 2, 2, Timestamp.valueOf("2022-05-27 01:01:00.0"), Timestamp.valueOf("2022-05-27 23:01:00.0") };
        Object[] obj2 = { 2, 2, 2, Timestamp.valueOf("2022-05-28 01:01:00.0"), Timestamp.valueOf("2022-05-28 23:01:00.0") };


    }


    @Test
    public void execute() throws  SQLException {
        processInformation();
        when(technicalRepository.existsById(1)).thenReturn(true);
        when(technicalRequestRepository.findServiceByWeekAndTechical(consultDTO.getWeek(), consultDTO.getIdTechnical())).thenReturn(consultWeek);
        ConsultDTO response = this.consultTechnicalRequestService.execute(consultDTO);
        assertNotNull(response);

    }
    @Test
    public void checkOvertimeTrue(){
        boolean response = this.consultTechnicalRequestService.checkOvertime(10,10,10,20);
        assertTrue(response);

    }
    @Test
    public void checkOvertimeFalse(){
        boolean response = this.consultTechnicalRequestService.checkOvertime(10,10,10,10);
        assertFalse(response);

    }

    @Test
    public void executeSunday() throws  SQLException {
        processInformationSunday();
        when(technicalRepository.existsById(1)).thenReturn(true);
        when(technicalRequestRepository.findServiceByWeekAndTechical(consultDTO.getWeek(), consultDTO.getIdTechnical())).thenReturn(consultWeek);
        ConsultDTO response = this.consultTechnicalRequestService.execute(consultDTO);
        assertNotNull(response);

    }


    @Test
    public void executeMorningNightHoursAndNormalAndNightHours() throws  SQLException {
        processInformationMorningNightHoursAndNormalAndNightHours();
        when(technicalRepository.existsById(1)).thenReturn(true);
        when(technicalRequestRepository.findServiceByWeekAndTechical(consultDTO.getWeek(), consultDTO.getIdTechnical())).thenReturn(consultWeek);
        ConsultDTO response = this.consultTechnicalRequestService.execute(consultDTO);
        assertNotNull(response);

    }


    @Test
    public void executeSundayExtra() throws  SQLException {
        processInformationSundayExtra();
        when(technicalRepository.existsById(1)).thenReturn(true);
        when(technicalRequestRepository.findServiceByWeekAndTechical(consultDTO.getWeek(), consultDTO.getIdTechnical())).thenReturn(consultWeek);
        ConsultDTO response = this.consultTechnicalRequestService.execute(consultDTO);
        assertNotNull(response);

    }






}