package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class PaymentProvider {

    @RequestMapping(
            value = "/portal",
            method = GET,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentResult pay(@RequestBody Payment payment) {

        return new PaymentResult("{\"status\":\"SUCCESS\"}");
    }
}
