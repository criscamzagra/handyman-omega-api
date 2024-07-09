package com.co.ias.Handyman.application.technicalRequest.services;

import com.co.ias.Handyman.application.technicalRequest.domain.TechnicalRequest;
import com.co.ias.Handyman.application.technicalRequest.model.ConsultDTO;
import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDBO;
import com.co.ias.Handyman.application.technicalRequest.model.TechnicalRequestDTO;
import com.co.ias.Handyman.application.technicalRequest.ports.in.ConsultTechnicalRequestUseCase;
import com.co.ias.Handyman.application.technicalRequest.ports.out.TechnicalRequestRepository;
import com.co.ias.Handyman.shared.errors.BadRequestException;
import com.co.ias.Handyman.shared.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultTechnicalRequestService implements ConsultTechnicalRequestUseCase {


    List<TechnicalRequestDTO> listResult = new ArrayList<>();

    @Autowired
    private final TechnicalRequestRepository technicalRequestRepository;

    @Autowired
    private final TechnicalRepository technicalRepository;

    private int morningNightHours, normalHours, nightHoursNight, sundayHours, nightExtraMorning, normalExtraHours,
            nightExtraHours, extraSunday, totalHours, checkExtra;

    boolean result = true;

    boolean extra = false;

    public ConsultTechnicalRequestService(TechnicalRequestRepository technicalRequestRepository, TechnicalRepository technicalRepository) {
        this.technicalRequestRepository = technicalRequestRepository;
        this.technicalRepository = technicalRepository;
    }


    @Override
    public ConsultDTO execute(ConsultDTO consultDTO) throws SQLException, BadRequestException {
        boolean existsIdTech = technicalRepository.existsById(consultDTO.getIdTechnical());
        consultDTO = validTechnical(consultDTO, existsIdTech);
        return consultDTO;
    }


    public ConsultDTO validTechnical(ConsultDTO consultDTO, boolean existsIdTech) throws SQLException {
        if (existsIdTech) {
            consultDTO = validWeek(consultDTO);
        } else {
            throw new SQLException(Constant.DATA_NOT_FOUND);
        }
        return consultDTO;
    }

    public ConsultDTO validWeek(ConsultDTO consultDTO) {
        List<TechnicalRequestDBO> consultWeekAndTechnical = technicalRequestRepository.findServiceByWeekAndTechical(consultDTO.getWeek(), consultDTO.getIdTechnical());
        if (consultWeekAndTechnical.isEmpty()) {
            throw new BadRequestException(Constant.DATA_NOT_FOUND);
        } else {
            getTechnicianListWeek(consultWeekAndTechnical);
            calculateHours(listResult);
            consultDTO = calculationTotalHours(consultDTO.getWeek(), consultDTO.getIdTechnical());
            clean();
        }
        return consultDTO;
    }


    public void clean() {
        extra = false;
        listResult.clear();
    }

    public ConsultDTO calculationTotalHours(Integer week, Integer idTechnical) {
        totalHours = normalHours + morningNightHours + nightHoursNight + sundayHours + normalExtraHours +
                nightExtraMorning + nightExtraHours + extraSunday;

        ConsultDTO consultDTO = new ConsultDTO(idTechnical, week, totalHours,
                normalHours, morningNightHours, nightHoursNight,
                sundayHours, normalExtraHours, nightExtraMorning, nightExtraHours, extraSunday);
        return consultDTO;
    }


    public void getTechnicianListWeek(List<TechnicalRequestDBO> consultWeekAndTechnical) {
        for (TechnicalRequestDBO item : consultWeekAndTechnical) {
            TechnicalRequestDBO technicalRequestDBO = new TechnicalRequestDBO(item.getTechnicalRequestId(), item.getRequestId(), item.getTechnicalId(), item.getStartDay(), item.getEndDay());
            TechnicalRequest technicalRequest = technicalRequestDBO.toDomain();
            TechnicalRequestDTO technicalRequestDTO = TechnicalRequestDTO.fromDomain(technicalRequest);
            listResult.add(technicalRequestDTO);
        }
    }


    public void calculateHours(List<TechnicalRequestDTO> listResult) {
        initializeVariables();
        for (TechnicalRequestDTO register : listResult) {
            if (!register.getStartDay().getDayOfWeek().name().equals(Constant.SUNDAY)) {
                if (register.getStartDay().getDayOfWeek().name().equals(register.getEndDay().getDayOfWeek().name())
                        && register.getStartDay().getHour() < register.getEndDay().getHour()) {
                    validationOfHoursForDays(register);
                }
            } else {
                if (register.getStartDay().getDayOfWeek() == register.getEndDay().getDayOfWeek()) {
                    calculateSundayHours(register);
                }
            }
            extra = checkOvertime(morningNightHours, normalHours, nightHoursNight, sundayHours);
        }
    }

    public void validationOfHoursForDays(TechnicalRequestDTO register) {
        if (register.getEndDay().getHour() <= Constant.CHANGE_MORNING) {
            calculateMorningNightHours(register);
        } else if (register.getStartDay().getHour() >= Constant.CHANGE_MORNING) {
            generalHourCalculation(register);
        } else if (register.getStartDay().getHour() <= Constant.CHANGE_MORNING && register.getEndDay().getHour() >= Constant.CHANGE_NIGHT) {
            calculateMorningNightHoursAndNormalAndNightHours(register);
        } else if (register.getStartDay().getHour() <= Constant.CHANGE_MORNING && register.getEndDay().getHour()
                <= Constant.CHANGE_NIGHT && register.getEndDay().getHour() > Constant.CHANGE_MORNING) {
            calculateMorningAndNormalNightHours(register);
        }
    }

    public void generalHourCalculation(TechnicalRequestDTO register) {
        if (register.getEndDay().getHour() <= Constant.CHANGE_NIGHT) {
            calculateNormalHours(register);
        } else if (register.getEndDay().getHour() >= Constant.CHANGE_NIGHT
                && register.getStartDay().getHour() <= Constant.CHANGE_NIGHT) {
            calculateNormalAndNightHours(register);
        } else if (register.getStartDay().getHour() >= Constant.CHANGE_NIGHT) {
            calculateNightHoursNight(register);
        }
    }

    public void calculateSundayHours(TechnicalRequestDTO register) {
        if (extra || checkExtra > Constant.VALUE_MAX) {
            extraSunday += register.getEndDay().getHour() - register.getStartDay().getHour();
        } else {
            sundayHours += register.getEndDay().getHour() - register.getStartDay().getHour();
            checkExtra += register.getEndDay().getHour() - register.getStartDay().getHour();
            validExtraSundayHours();
        }
    }

    public void validExtraSundayHours() {
        if (checkExtra > Constant.VALUE_MAX) {
            extraSunday += checkExtra - Constant.VALUE_MAX;
            sundayHours = sundayHours - extraSunday;
        }
        if (extra) {
            extraSunday += checkExtra - Constant.VALUE_MAX;
            sundayHours = sundayHours - extraSunday;
        }
    }

    public void calculateMorningAndNormalNightHours(TechnicalRequestDTO register) {
        if (extra || checkExtra > Constant.VALUE_MAX) {
            nightExtraMorning += Constant.CHANGE_MORNING - register.getStartDay().getHour();
            normalExtraHours += register.getEndDay().getHour() - Constant.CHANGE_MORNING;
        } else {
            morningNightHours += Constant.CHANGE_MORNING - register.getStartDay().getHour();
            checkExtra += Constant.CHANGE_MORNING - register.getStartDay().getHour();
            validExtraMorningNormalNightHours(register);
        }
    }

    public void validExtraMorningNormalNightHours(TechnicalRequestDTO register) {
        if (checkExtra > Constant.VALUE_MAX) {
            nightExtraMorning += checkExtra - Constant.VALUE_MAX;
            morningNightHours = morningNightHours - nightExtraMorning;
            normalExtraHours += register.getEndDay().getHour() - Constant.CHANGE_MORNING;
        } else {
            extraValidationMorningNormalNightHours(register);
        }
    }

    public void extraValidationMorningNormalNightHours(TechnicalRequestDTO register) {
        if (checkExtra >= Constant.VALUE_MAX) {
            normalExtraHours += register.getEndDay().getHour() - Constant.CHANGE_MORNING;
        } else {
            normalHours += register.getEndDay().getHour() - Constant.CHANGE_MORNING;
            checkExtra += register.getEndDay().getHour() - Constant.CHANGE_MORNING;
            if (checkExtra > Constant.VALUE_MAX) {
                normalExtraHours += checkExtra - Constant.VALUE_MAX;
                normalHours = normalHours - normalExtraHours;
            }
        }
    }

    public void calculateMorningNightHoursAndNormalAndNightHours(TechnicalRequestDTO register) {
        if (extra || checkExtra > Constant.VALUE_MAX) {
            nightExtraMorning += Constant.CHANGE_MORNING - register.getStartDay().getHour();
            normalExtraHours += Constant.CHANGE_NIGHT - Constant.CHANGE_MORNING;
            nightExtraHours += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
        } else {
            morningNightHours += Constant.CHANGE_MORNING - register.getStartDay().getHour();
            checkExtra += Constant.CHANGE_MORNING - register.getStartDay().getHour();
            ;
            calculateExtraMorningNightHoursAndNormalAndNightHours(register);
        }
    }

    public void calculateExtraMorningNightHoursAndNormalAndNightHours(TechnicalRequestDTO register) {
        if (checkExtra > Constant.VALUE_MAX) {
            nightExtraMorning += checkExtra - Constant.VALUE_MAX;
            morningNightHours = morningNightHours - nightExtraMorning;
            normalExtraHours += Constant.CHANGE_NIGHT - Constant.CHANGE_MORNING;
            nightExtraHours += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
        } else {
            normalHours += Constant.CHANGE_NIGHT - Constant.CHANGE_MORNING;
            checkExtra += Constant.CHANGE_NIGHT - Constant.CHANGE_MORNING;
            validateExtraMorningNightHoursAndNormalAndNightHours(register);
            nightHoursNight += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
            checkExtra += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
        }
    }

    public void validateExtraMorningNightHoursAndNormalAndNightHours(TechnicalRequestDTO register) {
        if (checkExtra > Constant.VALUE_MAX) {
            normalExtraHours += checkExtra - Constant.VALUE_MAX;
            normalHours = normalHours - normalExtraHours;
            nightExtraHours += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
        }
    }

    public void calculateNightHoursNight(TechnicalRequestDTO register) {
        if (extra || checkExtra > Constant.VALUE_MAX) {
            nightExtraHours += register.getEndDay().getHour() - register.getStartDay().getHour();
        } else {
            nightHoursNight += register.getEndDay().getHour() - register.getStartDay().getHour();
        }
    }

    public void calculateNormalAndNightHours(TechnicalRequestDTO register) {
        if (extra || checkExtra > Constant.VALUE_MAX) {
            normalExtraHours += Constant.CHANGE_NIGHT - register.getStartDay().getHour();
            nightExtraHours += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;

        } else {
            normalHours += Constant.CHANGE_NIGHT - register.getStartDay().getHour();
            checkExtra += Constant.CHANGE_NIGHT - register.getStartDay().getHour();
            validateExtraNormalAndNightHours(register);

        }
    }

    public void validateExtraNormalAndNightHours(TechnicalRequestDTO register) {
        if (checkExtra > Constant.VALUE_MAX) {
            normalExtraHours += checkExtra - Constant.VALUE_MAX;
            normalHours = normalHours - normalExtraHours;
            nightExtraHours += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
        } else {
            nightHoursNight += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
            checkExtra += register.getEndDay().getHour() - Constant.CHANGE_NIGHT;
            if (checkExtra > Constant.VALUE_MAX) {
                nightExtraHours += checkExtra - Constant.VALUE_MAX;
                nightHoursNight = nightHoursNight - nightExtraHours;
            }
        }
    }

    public void calculateNormalHours(TechnicalRequestDTO register) {
        if (extra || checkExtra > Constant.VALUE_MAX) {
            normalExtraHours += register.getEndDay().getHour() - register.getStartDay().getHour();
        } else {
            normalHours += register.getEndDay().getHour() - register.getStartDay().getHour();
            checkExtra += register.getEndDay().getHour() - register.getStartDay().getHour();
            validateExtraNormalHours();
        }
    }

    public void validateExtraNormalHours() {
        if (checkExtra > Constant.VALUE_MAX) {
            normalExtraHours += checkExtra - Constant.VALUE_MAX;
            normalHours = normalHours - normalExtraHours;
        }
    }

    public void calculateMorningNightHours(TechnicalRequestDTO register) {
        if (extra || checkExtra > Constant.VALUE_MAX) {
            nightExtraMorning += register.getEndDay().getHour() - register.getStartDay().getHour();
        } else {
            morningNightHours += register.getEndDay().getHour() - register.getStartDay().getHour();
            checkExtra += register.getEndDay().getHour() - register.getStartDay().getHour();
            if (checkExtra > Constant.VALUE_MAX) {
                nightExtraMorning += checkExtra - Constant.VALUE_MAX;
                morningNightHours = morningNightHours - nightExtraMorning;
            }
        }
    }

    public void initializeVariables() {
        morningNightHours = 0;
        normalHours = 0;
        nightHoursNight = 0;
        sundayHours = 0;
        nightExtraMorning = 0;
        normalExtraHours = 0;
        nightExtraHours = 0;
        extraSunday = 0;
        checkExtra = 0;
    }


    public boolean checkOvertime(int horasNocturnasMañana, int horasNormales, int horasNocturnasNoche, int horasDominical) {
        return horasNocturnasMañana + horasNormales + horasNocturnasNoche + horasDominical >= Constant.VALUE_MAX;

    }


}
