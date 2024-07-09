package com.co.ias.Handyman.application.technical.services;

import com.co.ias.Handyman.application.technical.model.TechnicalDBO;
import com.co.ias.Handyman.application.technical.ports.in.ExistsTechnicalUseCase;
import com.co.ias.Handyman.application.technical.ports.out.TechnicalRepository;
import com.co.ias.Handyman.application.technicalRequest.services.CreateTechnicalRequestService;

import java.util.Optional;

public class ExistsIdTechnicalService  implements ExistsTechnicalUseCase {

    private final TechnicalRepository technicalRepository;

    public ExistsIdTechnicalService(TechnicalRepository technicalRepository) {
        this.technicalRepository = technicalRepository;
    }

    @Override
    public TechnicalDBO execute(TechnicalDBO technicalDBO){



        return technicalDBO;

    }

}
