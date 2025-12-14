package com.ecommerce.domain.exception;

/**
 * Exception thrown when an invalid discount code is used.
 */
public class InvalidDiscountException extends DomainException {

    private final String discountCode;

    public InvalidDiscountException(String discountCode, String reason) {
        super(String.format("Invalid discount code '%s': %s", discountCode, reason));
        this.discountCode = discountCode;
    }

    public String getDiscountCode() {
        return discountCode;
    }
}
