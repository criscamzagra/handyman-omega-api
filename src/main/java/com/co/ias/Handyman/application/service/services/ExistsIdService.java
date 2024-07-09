package com.co.ias.Handyman.application.service.services;

import com.co.ias.Handyman.application.service.model.ServiceDBO;
import com.co.ias.Handyman.application.service.ports.in.ExistsIdServiceUseCase;

import java.sql.SQLException;

public class ExistsIdService implements ExistsIdServiceUseCase {



    @Override
    public ServiceDBO execute(ServiceDBO serviceDBO) throws SQLException {
        return null;
    }
}
