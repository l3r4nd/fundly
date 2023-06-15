package com.fundly.app.common.exception;

public class PaymentDateCannotBeGreaterThanDueDateException extends RuntimeException {


    public PaymentDateCannotBeGreaterThanDueDateException(String message) {
        super(message);
    }
}
