package com.ecommerce.domain.shipping;

/**
 * Enumeration of shipping methods.
 */
public enum ShippingMethod {
    /**
     * Standard shipping (5-7 business days)
     */
    STANDARD,

    /**
     * Express shipping (2-3 business days)
     */
    EXPRESS,

    /**
     * Overnight shipping (next business day)
     */
    OVERNIGHT,

    /**
     * Same day delivery
     */
    SAME_DAY,

    /**
     * Store pickup
     */
    PICKUP,

    /**
     * Free shipping
     */
    FREE
}
