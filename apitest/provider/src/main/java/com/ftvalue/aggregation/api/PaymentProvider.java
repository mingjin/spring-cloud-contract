package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class PaymentProvider {

    @RequestMapping(
            value = "/portal",
            method = GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentResult pay(@RequestParam("order_no") String orderNo, @RequestParam("total_fee") float totalFee) {

        return new PaymentResult().status("SUCCESS").amount(totalFee);
    }
}
