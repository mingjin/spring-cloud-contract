package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentResult;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "payment.service.baseUrl=http://localhost:${wiremock.server.port}", webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureWireMock(port = 0)
@DirtiesContext
public class PaymentConsumerTests {

    @Value("classpath:mappings/makeASuccessfulPayment.json")
    private Resource makeASuccessfulPayment;

    @Autowired
    private PaymentConsumer consumer;

    @Autowired
    private WireMockServer server;

    @Test
    public void shouldBeSuccessfulToMakeAPaymentGivenCorrectInfo() throws Exception {
        // stub:
        server.addStubMapping(StubMapping.buildFrom(StreamUtils.copyToString(
                makeASuccessfulPayment.getInputStream(), Charset.forName("UTF-8"))));

        // given:
        Payment payment = new Payment("95ff8e3b2ff06eb4f894e46fb028ccedc8d2294e068632e810c10bg6adgegg05")
                .set("order_no", "20170413232809").set("charset", "GBK").set("service", "online_pay")
                .set("seller_email", "game211@126.com").set("merchant_ID", "100000000001986").set("isApp", "web")
                .set("paymethod", "bankPay").set("notify_url", "https://test.payworth.net/notify_url.jsp")
                .set("title", "1").set("body", "1").set("payment_type", 1).set("total_fee", 0.11F)
                .set("return_url", "https://test.payworth.net/return_url.jsp");

        // when:
        PaymentResult paymentResult = consumer.pay(payment);

        // then:
        assertThat(paymentResult.getStatus()).isEqualTo("SUCCESS");
        assertThat(paymentResult.getAmount()).isEqualTo(124);
    }

    @Test
    public void shouldBeFailedToMakeAPaymentDueToIncorrectPaymethod() throws IOException {
        // stub:
        final float amount = 100.11F;
        server.stubFor(get(urlPathEqualTo("/portal"))
                .withQueryParam("paymethod", notMatching("bankPay"))
                .willReturn(aResponse().withHeader("content-type", "application/json").withStatus(200)
                        .withBody("{\"status\": \"FAILED\", \"amount\": " + amount + "}")));

        // given:
        Payment payment = new Payment("").set("paymethod", "alipay").set("amount", amount);

        // when:
        PaymentResult paymentResult = consumer.pay(payment);

        // then:
        assertThat(paymentResult.getStatus()).isEqualTo("FAILED");
        assertThat(paymentResult.getAmount()).isEqualTo(amount);
    }

}
