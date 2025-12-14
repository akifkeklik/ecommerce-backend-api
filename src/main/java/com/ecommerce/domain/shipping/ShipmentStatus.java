package com.ecommerce.domain.shipping;

/**
 * Enumeration of shipment statuses.
 */
public enum ShipmentStatus {
    /**
     * Shipment is pending
     */
    PENDING,

    /**
     * Shipment is being prepared
     */
    PROCESSING,

    /**
     * Shipment has been shipped
     */
    SHIPPED,

    /**
     * Shipment is in transit
     */
    IN_TRANSIT,

    /**
     * Shipment is out for delivery
     */
    OUT_FOR_DELIVERY,

    /**
     * Shipment has been delivered
     */
    DELIVERED,

    /**
     * Delivery was attempted but failed
     */
    DELIVERY_FAILED,

    /**
     * Shipment was returned to sender
     */
    RETURNED
}
