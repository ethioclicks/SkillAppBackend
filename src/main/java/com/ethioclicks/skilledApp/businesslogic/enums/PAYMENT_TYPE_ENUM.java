package com.ethioclicks.skilledApp.businesslogic.enums;

public enum PAYMENT_TYPE_ENUM {
    HOURLY("HOURLY"),
    SALARY("SALARY"),
    NEGOTIATE("NEGOTIATE");

    private final String paymentTypeName;

    private PAYMENT_TYPE_ENUM(String paymentTypeName) {
        this.paymentTypeName=paymentTypeName;
    }

    public String paymentTypeName() {
        return paymentTypeName;
    }
}
