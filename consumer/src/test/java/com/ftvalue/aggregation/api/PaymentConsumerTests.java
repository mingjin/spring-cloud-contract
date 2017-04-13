package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;
import com.ftvalue.aggregation.api.model.PaymentBuilder;
import com.ftvalue.aggregation.api.model.PaymentResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext
public class PaymentConsumerTests {

    @Value("${payment.service.baseUrl}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentConsumer consumer;

    private MockRestServiceServer server;

    @Before
    public void before() {
        server = WireMockRestServiceServer.with(this.restTemplate)
                .baseUrl(this.baseUrl)
                .stubs("classpath:mappings/*.json").build();
    }

    @After
    public void after() {
        server.verify();
    }

    @Test
    public void shouldMakeAPaymentSuccessfullyGivenCorrectOrderInfo() throws Exception {
        // given:
        Payment payment = new PaymentBuilder().orderNo("316395233560657005").charset("GBK")
                .sellerEmail("402673978@qq.com").backUrl("http://www.baidu.com/").defaultBank("ALIPAY")
                .merchantID("100000000002004").isApp("web").notifyUrl("http://127.0.0.1:8090/return_url.jsp")
                .title("test").body("testproductDesc").paymentType(1).payMethod("directPay")
                .service("online_pay").totalFee(0.12).returnUrl("http://127.0.0.1:8090/return_url.jsp")
                .userIp("116.228.54.118").sign();

        // when:
        PaymentResult paymentResult = consumer.pay(payment);

        // then:
        assertThat(paymentResult.getBody()).isEqualTo("{\"status\":\"SUCCESS\"}");
    }

}
