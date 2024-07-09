package com.co.ias.Handyman.application.request.ports.out;

import com.co.ias.Handyman.application.request.domain.Request;
import com.co.ias.Handyman.application.request.domain.RequestId;

public interface RequestRepository {

    Integer store(Request request);
    Integer getId(RequestId requestId);
    Request update(Request request);
    Integer delete(RequestId requestId);
}
