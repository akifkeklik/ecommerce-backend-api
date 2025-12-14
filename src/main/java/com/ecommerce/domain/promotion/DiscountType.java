package com.ecommerce.domain.promotion;

/**
 * Enumeration of discount types.
 */
public enum DiscountType {
    /**
     * Fixed amount discount (e.g., $10 off)
     */
    FIXED_AMOUNT,

    /**
     * Percentage discount (e.g., 20% off)
     */
    PERCENTAGE,

    /**
     * Free shipping discount
     */
    FREE_SHIPPING,

    /**
     * Buy X get Y free
     */
    BUY_X_GET_Y,

    /**
     * Fixed price for specific products
     */
    FIXED_PRICE
}
