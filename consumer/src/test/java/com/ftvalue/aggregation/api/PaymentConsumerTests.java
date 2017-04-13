package com.ftvalue.aggregation.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;

import com.ftvalue.aggregation.api.model.Payment;
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

    /**
     * Payment Request Sample:
        http://114.215.242.9:18170/portal?
        order_no=316395233560657005&
        charset=GBK&
        seller_email=402673978@qq.com&
        backUrl=http://www.baidu.com/&
        defaultbank=ALIPAY&
        merchant_ID=100000000002004&
        isApp=web&
        notify_url=http://127.0.0.1:8090/return_url.jsp&
        title=test&
        body=testproductDesc&
        payment_type=1&
        payMethod=directPay&
        service=online_pay&
        total_fee=0.12&
        return_url=http://127.0.0.1:8090/return_url.jsp&
        userIp=116.228.54.118&
        sign=c37d0fa95cc38cd2680e5acb1c704751&
        sign_type=MD5
         */
    @Test
    public void shouldMakeAPaymentSuccessfullyGivenCorrectOrderInfo() throws Exception {
        server.addStubMapping(StubMapping.buildFrom(StreamUtils.copyToString(
                makeAPaymentSuccessfully.getInputStream(), Charset.forName("UTF-8"))));

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
        assertThat(paymentResult.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(paymentResult.getBody());
    }

}
