package com.ftvalue.aggregation.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
@AutoConfigureMockMvc
public class PaymentProviderTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldMakeASuccessfulPayment() throws Exception {
        float totalFee = 0.11F;

        mockMvc.perform(get("/portal").param("order_no", "20170413232809")
                .param("charparam", "GBK").param("service", "online_pay")
                .param("seller_email", "game211@126.com").param("merchant_ID", "100000000001986")
                .param("isApp", "web").param("paymethod", "bankPay").param("notify_url", "https://test.payworth.net/notify_url.jsp")
                .param("title", "1").param("body", "1").param("payment_type", "1")
                .param("total_fee", Float.toString(totalFee)).param("return_url", "https://test.payworth.net/return_url.jsp"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"status\":\"SUCCESS\",\"amount\":" + totalFee + "}"))
                .andDo(verify().stub("makeASuccessfulPayment"));
    }

}