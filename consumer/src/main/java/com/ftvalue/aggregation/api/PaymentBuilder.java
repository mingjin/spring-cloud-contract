package com.ftvalue.aggregation.api;

import com.ftvalue.aggregation.api.model.Payment;

public class PaymentBuilder {
    private Payment payment;
    private final String SIGN_TYPE = "MD5";

    public PaymentBuilder() {
        this.payment = new Payment();
    }

    public PaymentBuilder orderNo(String orderNo) {
        payment.setOrderNo(orderNo);
        return this;
    }

    public PaymentBuilder charset(String charset) {
        payment.setCharset(charset);
        return this;
    }

    public PaymentBuilder sellerEmail(String sellerEmail) {
        payment.setSellerEmail(sellerEmail);
        return this;
    }

    public PaymentBuilder backUrl(String backUrl) {
        payment.setBackUrl(backUrl);
        return this;
    }

    public PaymentBuilder defaultBank(String defaultBank) {
        payment.setDefaultBank(defaultBank);
        return this;
    }

    public PaymentBuilder merchantID(String merchantID) {
        payment.setMerchantID(merchantID);
        return this;
    }

    public PaymentBuilder isApp(String isApp) {
        payment.setIsApp(isApp);
        return this;
    }

    public PaymentBuilder notifyUrl(String notifyUrl) {
        payment.setNotifyUrl(notifyUrl);
        return this;
    }

    public PaymentBuilder title(String title) {
        payment.setTitle(title);
        return this;
    }

    public PaymentBuilder body(String body) {
        payment.setBody(body);
        return this;
    }

    public PaymentBuilder paymentType(int paymentType) {
        payment.setPaymentType(paymentType);
        return this;
    }

    public PaymentBuilder payMethod(String payMethod) {
        payment.setPayMethod(payMethod);
        return this;
    }

    public PaymentBuilder service(String service) {
        payment.setService(service);
        return this;
    }

    public PaymentBuilder totalFee(double totalFee) {
        payment.setTotalFee(totalFee);
        return this;
    }

    public PaymentBuilder returnUrl(String returnUrl) {
        payment.setReturnUrl(returnUrl);
        return this;
    }

    public PaymentBuilder userIp(String userIp) {
        payment.setUserIp(userIp);
        return this;
    }

    public Payment sign() {

        return payment;
    }
}
