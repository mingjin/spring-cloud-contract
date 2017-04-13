package com.ftvalue.aggregation.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("service")
public class PaymentConsumer {
}
