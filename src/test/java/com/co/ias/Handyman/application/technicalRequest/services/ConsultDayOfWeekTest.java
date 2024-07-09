package com.co.ias.Handyman.application.technicalRequest.services;

import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.model.ConsultDTO;
import com.co.ias.Handyman.application.technicalRequest.model.DayWeekDTO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDTO;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConsultDayOfWeekTest {

    @InjectMocks
    private ConsultDayOfWeek consultDayOfWeek;

    @Mock
    private TechnicalRequestRepository technicalRequestRepository;




    List<TechnicalRequestDTO> listResult;
    ConsultDTO consultDTO;
    TechnicalRequestDTO technicalRequestDTO;

    List<Object[]> consultWeek;



    public void processInformation() {
        listResult = new ArrayList<>();
        consultWeek = new ArrayList<>();
        consultDTO= new ConsultDTO( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        technicalRequestDTO =new TechnicalRequestDTO(1,1,1, LocalDateTime.now(),LocalDateTime.now());
        listResult.add(technicalRequestDTO);
        Object[] obj = { 1, 2, 2, Timestamp.valueOf("2022-05-24 01:01:00.0"), Timestamp.valueOf("2022-05-24 08:01:00.0") };
        Object[] obj1 = { 2, 2, 2, Timestamp.valueOf("2022-05-25 01:01:00.0"), Timestamp.valueOf("2022-05-25 08:01:00.0") };
        consultWeek.add(obj);
        consultWeek.add(obj1);

    }

    @Test
    public void execute() throws SQLException, FileNotFoundException, NoSuchFieldException {
        processInformation();
        when(technicalRequestRepository.findServiceByWeekAndTechical(consultDTO.getWeek(), consultDTO.getIdTechnical())).thenReturn(consultWeek);
        List<DayWeekDTO> listDayWeek = consultDayOfWeek.execute(consultDTO);
        assertNotNull(listDayWeek);
    }


}