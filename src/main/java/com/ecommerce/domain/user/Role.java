package com.ecommerce.domain.user;

/**
 * Enumeration of user roles for role-based access control.
 */
public enum Role {
    /**
     * Administrator with full system access
     */
    ADMIN,

    /**
     * Regular customer who can browse and purchase products
     */
    CUSTOMER,

    /**
     * Seller who can manage their own products and view orders
     */
    SELLER
}
