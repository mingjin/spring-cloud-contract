package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payment.service.baseUrl}")
    private String baseUrl;


    public PaymentResult pay(Payment payment) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<PaymentResult> response =
                restTemplate.exchange(this.baseUrl + "/portal", HttpMethod.GET,
                        new HttpEntity<>(payment, httpHeaders),
                        PaymentResult.class);

        return response.getBody();
    }
}
