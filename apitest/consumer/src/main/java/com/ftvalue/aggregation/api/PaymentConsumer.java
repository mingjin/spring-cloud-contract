package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PaymentConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${payment.service.baseUrl}")
    private String baseUrl;


    public PaymentResult pay(Payment payment) {
        final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.baseUrl).path("/portal")
                .queryParams(payment.toQueryParams());

        final ResponseEntity<PaymentResult> response = restTemplate.getForEntity(builder.build().encode().toUri(),
                PaymentResult.class);

        return response.getBody();
    }
}
