package com.ftvalue.aggregation.api.model;

public class Payment {

    private String orderNo;
    private String charset;
    private String sellerEmail;
    private String backUrl;
    private String defaultBank;
    private String merchantID;
    private String isApp;
    private String notifyUrl;
    private String title;
    private String body;
    private int paymentType;
    private String payMethod;
    private String service;
    private double totalFee;
    private String returnUrl;
    private String userIp;

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCharset() {
        return charset;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setDefaultBank(String defaultBank) {
        this.defaultBank = defaultBank;
    }

    public String getDefaultBank() {
        return defaultBank;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setIsApp(String isApp) {
        this.isApp = isApp;
    }

    public String getIsApp() {
        return isApp;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getUserIp() {
        return userIp;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }
}
