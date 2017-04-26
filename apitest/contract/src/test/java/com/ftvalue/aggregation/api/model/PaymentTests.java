package com.ftvalue.aggregation.api.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PaymentTests {

    /**
     * source:
     * body=1&charset=GBK&isApp=web&merchant_ID=100000000001986&notify_url=https://test.payworth.net/notify_url.jsp&order_no=20170413232809&payment_type=1&paymethod=bankPay&return_url=https://test.payworth.net/return_url.jsp&seller_email=game211@126.com&service=online_pay&title=1&total_fee=0.11
     * SecureCode:
     * 95ff8e3b2ff06eb4f894e46fb028ccedc8d2294e068632e810c10bg6adgegg05
     * target:
     * 9fd12d5bab0f003a4013403f1157f66f
     */
    @Test
    public void shouldGenerateQueryParameterString() {
        Payment payment = new Payment().secureCode("95ff8e3b2ff06eb4f894e46fb028ccedc8d2294e068632e810c10bg6adgegg05")
                .set("order_no", "20170413232809").set("charset", "GBK").set("service", "online_pay")
                .set("seller_email", "game211@126.com").set("merchant_ID", "100000000001986").set("isApp", "web")
                .set("paymethod", "bankPay").set("notify_url", "https://test.payworth.net/notify_url.jsp")
                .set("title", "1").set("body", "1").set("payment_type", 1).set("total_fee", 0.11F)
                .set("return_url","https://test.payworth.net/return_url.jsp");

        assertThat(payment.toString(), equalTo("body=1&charset=GBK&isApp=web&merchant_ID=100000000001986&notify_url=https://test.payworth.net/notify_url.jsp&order_no=20170413232809&payment_type=1&paymethod=bankPay&return_url=https://test.payworth.net/return_url.jsp&seller_email=game211@126.com&service=online_pay&title=1&total_fee=0.11"));
        assertThat(payment.getSignature(), equalTo("9fd12d5bab0f003a4013403f1157f66f"));
    }
}
