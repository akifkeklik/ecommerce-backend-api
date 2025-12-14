package com.ecommerce.domain.order;

/**
 * Enumeration of order statuses.
 */
public enum OrderStatus {
    /**
     * Order has been created but payment not yet received
     */
    PENDING,

    /**
     * Payment has been received and order is being processed
     */
    CONFIRMED,

    /**
     * Order is being prepared for shipment
     */
    PROCESSING,

    /**
     * Order has been shipped
     */
    SHIPPED,

    /**
     * Order is out for delivery
     */
    OUT_FOR_DELIVERY,

    /**
     * Order has been delivered successfully
     */
    DELIVERED,

    /**
     * Order has been cancelled
     */
    CANCELLED,

    /**
     * Order return has been requested
     */
    RETURN_REQUESTED,

    /**
     * Order has been returned
     */
    RETURNED,

    /**
     * Order has been refunded
     */
    REFUNDED,

    /**
     * Order failed (payment failed, etc.)
     */
    FAILED
}
