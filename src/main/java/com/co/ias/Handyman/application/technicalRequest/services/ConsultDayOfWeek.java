package com.co.ias.Handyman.application.technicalRequest.services;

import com.co.ias.Handyman.application.technicalRequest.domain.TechnicalRequest;
import com.co.ias.Handyman.application.technicalRequest.model.ConsultDTO;
import com.co.ias.Handyman.application.technicalRequest.model.DayWeekDTO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDTO;
import com.co.ias.Handyman.application.technicalRequest.ports.in.ConsultDayOfWeekUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;
import com.co.ias.Handyman.shared.util.Constant;
import com.co.ias.Handyman.shared.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class ConsultDayOfWeek implements ConsultDayOfWeekUseCase {

    Integer hoursTotal, startTime, endTime;
    List<TechnicalRequestDTO> listResult = new ArrayList<>();

    List<DayWeekDTO> listDayWeek = new ArrayList<>();


    @Autowired
    private final TechnicalRequestRepository technicalRequestRepository;

    public ConsultDayOfWeek(TechnicalRequestRepository technicalRequestRepository) {
        this.technicalRequestRepository = technicalRequestRepository;
    }

    @Override
    public List<DayWeekDTO> execute(ConsultDTO consultDTO) throws SQLException, FileNotFoundException, NoSuchFieldException {
        clean();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.YYYY_MM_DD_HH_MM_SS_S);
        getRequest(consultDTO, formatter);
        assignedDayWeek();
        return listDayWeek;

    }

    private void assignedDayWeek() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for(TechnicalRequestDTO register: listResult){
            DayWeekDTO dayWeekDTO = new DayWeekDTO();
            startTime=register.getStartDay().getHour();
            endTime=register.getEndDay().getHour();
            hoursTotal=endTime-startTime;
            dayWeekDTO.setDay(DateUtil.dayWeek(register.getStartDay().getDayOfWeek().name()));
            dayWeekDTO.setHours(hoursTotal);
            dayWeekDTO.setStartTime(register.getStartDay().format(formatter));
            dayWeekDTO.setEndTime(register.getEndDay().format(formatter));
            listDayWeek.add(dayWeekDTO);
        }
    }

    private void getRequest(ConsultDTO consultDTO, DateTimeFormatter formatter) {
        List<TechnicalRequestDBO> consult = technicalRequestRepository.findServiceByWeekAndTechical(consultDTO.getWeek(), consultDTO.getIdTechnical());
        for (TechnicalRequestDBO item : consult) {
            TechnicalRequestDBO technicalRequestDBO = new TechnicalRequestDBO(item.getTechnicalRequestId(), item.getRequestId(), item.getTechnicalId(), item.getStartDay(), item.getEndDay());
            TechnicalRequest technicalRequest = technicalRequestDBO.toDomain();
            TechnicalRequestDTO technicalRequestDTO = TechnicalRequestDTO.fromDomain(technicalRequest);
            listResult.add(technicalRequestDTO);

        }
    }

    private void clean() {
        listResult.clear();
        listDayWeek.clear();
    }
}
