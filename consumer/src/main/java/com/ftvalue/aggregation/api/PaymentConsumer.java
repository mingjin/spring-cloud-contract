package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
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
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.baseUrl).path("/portal")
                .queryParams(payment.toQueryParams());

        final ResponseEntity<PaymentResult> response = restTemplate.exchange(builder.build().encode().toUri(),
                HttpMethod.GET, new HttpEntity<>(headers), PaymentResult.class);

        return response.getBody();
    }
}
