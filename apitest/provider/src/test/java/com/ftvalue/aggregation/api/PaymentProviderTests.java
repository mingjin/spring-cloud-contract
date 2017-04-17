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
        mockMvc.perform(get("/portal").param("amount", "100.11"))
                .andExpect(status().isOk())
                .andDo(verify().stub("makeASuccessfulPayment"));
    }

}