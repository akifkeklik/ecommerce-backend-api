package com.ecommerce.domain.product;

/**
 * Enumeration of product statuses.
 */
public enum ProductStatus {
    /**
     * Product is being prepared and not yet visible
     */
    DRAFT,

    /**
     * Product is active and available for sale
     */
    ACTIVE,

    /**
     * Product is temporarily unavailable
     */
    INACTIVE,

    /**
     * Product has been discontinued
     */
    ARCHIVED,

    /**
     * Product is out of stock
     */
    OUT_OF_STOCK
}
