package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties("consumer")
public class PaymentConsumer {

    private final RestTemplate restTemplate;

    private String server = "localhost";

    private int port = 18170;

    public PaymentConsumer() {
        restTemplate = new RestTemplate();
    }

    public PaymentResult pay(Payment payment) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<PaymentResult> response =
                restTemplate.exchange("http://" + server + ":" + port + "/portal", HttpMethod.GET,
                        new HttpEntity<>(payment, httpHeaders),
                        PaymentResult.class);

        return response.getBody();
    }
}
