package com.co.ias.Handyman.infraestructure.controllers;

import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.model.ConsultDTO;

import com.co.ias.Handyman.application.technicalRequest.model.DayWeekDTO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;

import com.co.ias.Handyman.application.technicalRequest.ports.in.ConsultDayOfWeekUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.in.ConsultTechnicalRequestUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.in.CreateTechnicalRequestUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;
import com.co.ias.Handyman.shared.errors.BadRequestException;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class TechnicalRequestControllerTest {

    @InjectMocks
    private TechnicalRequestController technicalRequestController;

    @Mock
    private CreateTechnicalRequestUseCase createTechnicalRequestUseCase;

    @Mock
    private ConsultTechnicalRequestUseCase consultTechnicalRequestUseCase;
    @Mock
    private  TechnicalRepository technicalRepository;

    @Mock
    private ConsultDayOfWeekUseCase consultDayOfWeekUseCase;
    @Mock
    private TechnicalRequestRepository technicalRequestRepository;
    TechnicalRequestDBO technicalRequest;
    List<DayWeekDTO> list;
    DayWeekDTO dayWeekDTO;

    List<Object[]> consultWeek;



    public void processInformation(){
        consultWeek = new ArrayList<>();
        list= new ArrayList<>();
        technicalRequest = new TechnicalRequestDBO(1,1,2, LocalDateTime.now(),LocalDateTime.now());
        dayWeekDTO= new DayWeekDTO("lunes",22,"1:00","10:00");
        list.add(dayWeekDTO);
        Object[] obj = { 1, 2, 2, Timestamp.valueOf("2022-05-24 01:01:00.0"), Timestamp.valueOf("2022-05-24 08:01:00.0") };
        Object[] obj1 = { 2, 2, 2, Timestamp.valueOf("2022-05-25 01:01:00.0"), Timestamp.valueOf("2022-05-25 08:01:00.0") };
        consultWeek.add(obj);
        consultWeek.add(obj1);
    }

    @Test
    public void create() throws SQLException, FileNotFoundException, NoSuchFieldException{
        processInformation();
        technicalRequest.toDomain();
        when(createTechnicalRequestUseCase.execute(technicalRequest)).thenReturn(technicalRequest);
        ResponseEntity<?> response = technicalRequestController.createTechRequest(technicalRequest);
        assertNotNull(response);
    }

    @Test
    public void createSQLException(){
        processInformation();
        try {
            when(createTechnicalRequestUseCase.execute(technicalRequest)).thenThrow(new SQLException());
            technicalRequestController.createTechRequest(technicalRequest);
        }catch(SQLException | FileNotFoundException | NoSuchFieldException exception){
            assertThat(exception.getMessage(), is("Something bad happened"));

        }
    }

    @Test
    public void consultWeekTechnical() throws SQLException, FileNotFoundException, NoSuchFieldException{
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setWeek(1);
        consultDTO.setIdTechnical(2);
        when(consultTechnicalRequestUseCase.execute(consultDTO)).thenReturn(consultDTO);
        ResponseEntity<?> response = technicalRequestController.consultWeekTechnical(1,2);
        assertNotNull(response);
    }



    @Test
    public void createFileNotFoundException(){
        processInformation();
        try {
            when(createTechnicalRequestUseCase.execute(technicalRequest)).thenThrow(new FileNotFoundException());
            technicalRequestController.createTechRequest(technicalRequest);
        }catch(SQLException | FileNotFoundException | NoSuchFieldException exception){
            assertThat(exception.getMessage(), is("Something bad happened"));

        }
    }

    @Test
    public void createNoSuchFieldException(){
        processInformation();
        try {
            when(createTechnicalRequestUseCase.execute(technicalRequest)).thenThrow(new NoSuchFieldException());
            technicalRequestController.createTechRequest(technicalRequest);
        }catch(SQLException | FileNotFoundException | NoSuchFieldException exception){
            assertThat(exception.getMessage(), is("Something bad happened"));

        }
    }

    @Test
    public void createDateTimeException(){
        processInformation();
        try {
            when(createTechnicalRequestUseCase.execute(technicalRequest)).thenThrow(DateTimeException.class);
            technicalRequestController.createTechRequest(technicalRequest);
        }catch(DateTimeException | SQLException | FileNotFoundException | NoSuchFieldException exception){
            assertThat(exception.getMessage(), is("Something bad happened"));

        }
    }

    @Test
    public void createIllegalArgumentException(){
        processInformation();
        try {
            when(createTechnicalRequestUseCase.execute(technicalRequest)).thenThrow(new IllegalArgumentException());
            technicalRequestController.createTechRequest(technicalRequest);
        }catch(IllegalArgumentException | SQLException | FileNotFoundException | NoSuchFieldException exception){
            assertThat(exception.getMessage(), is("Something bad happened"));

        }
    }


    @Test
    public void consultWeekTechnicalIllegalArgumentException() throws SQLException, FileNotFoundException, NoSuchFieldException{
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setWeek(1);
        consultDTO.setIdTechnical(2);
        try {
            when(consultTechnicalRequestUseCase.execute(consultDTO)).thenThrow(new IllegalArgumentException());
            technicalRequestController.consultWeekTechnical(1,2);

        }catch (IllegalArgumentException exception){
            assertThat(exception.getMessage(), is("Something bad happened"));
        }

    }

    @Test
    public void consultWeekTechnicalSQLException() throws SQLException, FileNotFoundException, NoSuchFieldException {
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setWeek(1);
        consultDTO.setIdTechnical(2);

        try {
            when(consultTechnicalRequestUseCase.execute(consultDTO)).thenThrow(new SQLException());
           technicalRequestController.consultWeekTechnical(1,2);

        }catch (SQLException e){
            assertThat(e.getMessage(), is("Something bad happened"));
      }



    }

    @Test
    public void consultWeekTechnicalBadRequestException() throws SQLException, FileNotFoundException, NoSuchFieldException{
        ConsultDTO consultDTO = new ConsultDTO();
        consultDTO.setWeek(1);
        consultDTO.setIdTechnical(2);
        try {
            when(consultTechnicalRequestUseCase.execute(consultDTO)).thenThrow(BadRequestException.class);
            technicalRequestController.consultWeekTechnical(1,2);


        }catch (BadRequestException exception){
            assertThat(exception.getMessage(), is("Something bad happened"));
        }

    }








}