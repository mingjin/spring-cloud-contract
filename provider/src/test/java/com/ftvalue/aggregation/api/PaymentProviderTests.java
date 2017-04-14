package com.ftvalue.aggregation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftvalue.aggregation.api.model.Payment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureRestDocs(outputDir = "build/snippets")
@AutoConfigureMockMvc
public class PaymentProviderTests {

    @Autowired
    private MockMvc mockMvc;

    private JacksonTester<Payment> json;

    @Before
    public void setup() {
        ObjectMapper objectMappper = new ObjectMapper();
        JacksonTester.initFields(this, objectMappper);
    }

    @Test
    public void shouldMakeAPaymentSuccessfully() throws Exception {
        Payment payment = new Payment(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/portal").contentType(MediaType.APPLICATION_JSON)
                .content(json.write(payment).getJson()))
                .andExpect(status().isOk())
                .andDo(verify().stub("makeAPaymentSuccessfully"));
    }

}