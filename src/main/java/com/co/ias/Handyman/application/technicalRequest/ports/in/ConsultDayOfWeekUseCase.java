package com.co.ias.Handyman.application.technicalRequest.ports.in;

import com.co.ias.Handyman.application.technicalRequest.model.ConsultDTO;
import com.co.ias.Handyman.application.technicalRequest.model.DayWeekDTO;
import com.co.ias.Handyman.commons.UseCase;

import java.util.List;

public interface ConsultDayOfWeekUseCase  extends UseCase<ConsultDTO, List<DayWeekDTO>> {
}
