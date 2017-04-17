package com.ftvalue.aggregation.api.model;

public class PaymentResult {
    private String status;
    private float amount;

    public String getStatus() {
        return status;
    }

    public float getAmount() {
        return amount;
    }

    public PaymentResult status(String status) {
        this.status = status;
        return this;
    }

    public PaymentResult amount(float amount) {
        this.amount = amount;
        return this;
    }
}
