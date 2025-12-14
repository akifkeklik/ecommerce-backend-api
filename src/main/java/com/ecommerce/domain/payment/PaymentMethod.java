package com.ecommerce.domain.payment;

/**
 * Enumeration of supported payment methods.
 */
public enum PaymentMethod {
    /**
     * Credit card payment via Stripe
     */
    CREDIT_CARD,

    /**
     * Debit card payment
     */
    DEBIT_CARD,

    /**
     * PayPal payment
     */
    PAYPAL,

    /**
     * Bank transfer
     */
    BANK_TRANSFER,

    /**
     * Cash on delivery
     */
    CASH_ON_DELIVERY,

    /**
     * Digital wallet (Apple Pay, Google Pay, etc.)
     */
    DIGITAL_WALLET
}
