package com.ecommerce.domain.payment;

/**
 * Enumeration of payment statuses.
 */
public enum PaymentStatus {
    /**
     * Payment is pending
     */
    PENDING,

    /**
     * Payment is being processed
     */
    PROCESSING,

    /**
     * Payment was successful
     */
    COMPLETED,

    /**
     * Payment failed
     */
    FAILED,

    /**
     * Payment was cancelled
     */
    CANCELLED,

    /**
     * Payment was refunded
     */
    REFUNDED,

    /**
     * Payment was partially refunded
     */
    PARTIALLY_REFUNDED,

    /**
     * Payment is disputed/chargebacked
     */
    DISPUTED
}
