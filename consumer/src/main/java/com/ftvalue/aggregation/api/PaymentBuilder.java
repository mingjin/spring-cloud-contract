package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;

public class PaymentBuilder {
    public Payment build() {
        return new Payment();
    }

    public PaymentBuilder orderNo(String orderNo) {
        return this;
    }

    public PaymentBuilder charset(String charset) {
        return this;
    }

    public PaymentBuilder sellerEmail(String sellerEmail) {
        return this;
    }

    public PaymentBuilder backUrl(String backUrl) {
        return this;
    }

    public PaymentBuilder defaultBank(String defaultBank) {
        return this;
    }

    public PaymentBuilder merchantID(String merchantID) {
        return this;
    }

    public PaymentBuilder isApp(String isApp) {
        return this;
    }

    public PaymentBuilder notifyUrl(String notifyUrl) {
        return this;
    }

    public PaymentBuilder title(String title) {
        return this;
    }

    public PaymentBuilder body(String body) {
        return this;
    }

    public PaymentBuilder paymentType(int paymentType) {
        return this;
    }

    public PaymentBuilder payMethod(String payMethod) {
        return this;
    }

    public PaymentBuilder service(String service) {
        return this;
    }

    public PaymentBuilder totalFee(double totalFee) {
        return this;
    }

    public PaymentBuilder returnUrl(String returnUrl) {
        return this;
    }

    public PaymentBuilder userIp(String userIp) {
        return this;
    }

    public PaymentBuilder sign(String sign) {
        return this;
    }

    public PaymentBuilder signType(String signType) {
        return this;
    }
}
