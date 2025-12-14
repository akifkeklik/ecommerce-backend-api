package com.ecommerce.domain.exception;

/**
 * Exception thrown when a payment operation fails.
 */
public class PaymentException extends DomainException {

    private final String errorCode;

    public PaymentException(String message) {
        super(message);
        this.errorCode = null;
    }

    public PaymentException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PaymentException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
