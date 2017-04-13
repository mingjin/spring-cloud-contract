package com.ftvalue.aggregation.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentBuilder;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureWireMock(port=18170)
public class PaymentConsumerTests {

    @Autowired
    private PaymentConsumer consumer;

    @Value("classpath:mappings/makeAPaymentSuccessfully.json")
    private Resource makeAPaymentSuccessfully;

    @Autowired
    private WireMockServer server;

    @Test
    public void shouldMakeAPaymentSuccessfullyGivenCorrectOrderInfo() throws Exception {
        server.addStubMapping(StubMapping.buildFrom(StreamUtils.copyToString(
                makeAPaymentSuccessfully.getInputStream(), Charset.forName("UTF-8"))));

        // given:
        Payment payment = new PaymentBuilder().sign();

        // when:
        PaymentResult paymentResult = consumer.pay(payment);

        // then:
        assertThat(paymentResult.getBody()).isEqualTo("{\"status\":\"SUCCESS\"}");
    }

}
