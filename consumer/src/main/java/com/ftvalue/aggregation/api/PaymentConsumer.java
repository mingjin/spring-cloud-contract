package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties("service")
public class PaymentConsumer {

    private final RestTemplate restTemplate;

    private String server = "localhost";

    private int port = 18170;

    public PaymentConsumer() {
        restTemplate = new RestTemplate();
    }

    public PaymentResult pay(Payment payment) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");

        ResponseEntity<String> response =
                restTemplate.exchange("http://" + server + ":" + port + "/portal", HttpMethod.GET,
                        new HttpEntity<>(payment, httpHeaders),
                        String.class);

        PaymentResult paymentResult = new PaymentResult(response.getStatusCode(), response.getBody());

        return paymentResult;
    }
}