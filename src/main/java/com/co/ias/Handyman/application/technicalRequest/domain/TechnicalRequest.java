package com.co.ias.Handyman.application.technicalRequest.domain;

import com.co.ias.Handyman.application.request.domain.RequestId;
import com.co.ias.Handyman.application.technical.domain.TechnicalId;

import javax.persistence.Entity;
import javax.persistence.Table;


public class TechnicalRequest {

    private final TechnicalRequestId technicalRequestId;
    private final RequestId requestId;
    private final TechnicalId technicalId;
    private final StartDay startDay;
    private final EndDay endDay;



    public TechnicalRequest(TechnicalRequestId technicalRequestId, RequestId requestId, TechnicalId technicalId, StartDay startDay, EndDay endDay) {
        this.technicalRequestId = technicalRequestId;
        this.requestId = requestId;
        this.technicalId = technicalId;
        this.startDay = startDay;
        this.endDay = endDay;
    }



    public TechnicalRequestId getTechnicalRequestId() {
        return technicalRequestId;
    }

    public TechnicalId getTechnicalId() {
        return technicalId;
    }

    public RequestId getRequestId() {
        return requestId;
    }

    public StartDay getStartDay() {
        return startDay;
    }

    public EndDay getEndDay() {
        return endDay;
    }
}
