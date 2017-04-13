package com.ftvalue.aggregation.api;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;

import com.example.loan.LoanApplicationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StreamUtils;

import com.example.loan.model.Client;
import com.example.loan.model.LoanApplication;
import com.example.loan.model.LoanApplicationResult;
import com.example.loan.model.LoanApplicationStatus;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= com.example.loan.Application.class, properties="service.port=${wiremock.server.port}")
@AutoConfigureWireMock(port=0)
public class PaymentConsumerTests {

    @Autowired
    private LoanApplicationService service;

    @Value("classpath:mappings/makeAPaymentSuccessfully.json")
    private Resource makeAPaymentSuccessfully;

    @Autowired
    private WireMockServer server;

    @Test
    public void shouldMakeAPaymentSuccessfullyGivenCorrectOrderInfo() throws Exception {
        server.addStubMapping(StubMapping.buildFrom(StreamUtils.copyToString(
                makeAPaymentSuccessfully.getInputStream(), Charset.forName("UTF-8"))));
        // given:
        LoanApplication application = new LoanApplication(new Client("1234567890"),
                99999);
        // when:
        LoanApplicationResult loanApplication = service.loanApplication(application);

        // then:
        assertThat(loanApplication.getLoanApplicationStatus())
                .isEqualTo(LoanApplicationStatus.LOAN_APPLICATION_REJECTED);
        assertThat(loanApplication.getRejectionReason()).isEqualTo("Amount too high");
    }

}
