package com.ftvalue.aggregation.api.model;

import org.springframework.http.HttpStatus;

public class PaymentResult {
    private HttpStatus statusCode;
    private String body;

    public PaymentResult(HttpStatus statusCode, String body) {

        this.statusCode = statusCode;
        this.body = body;
    }


    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}
