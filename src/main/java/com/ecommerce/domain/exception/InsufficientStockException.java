package com.ecommerce.domain.exception;

/**
 * Exception thrown when there's insufficient stock for an operation.
 */
public class InsufficientStockException extends DomainException {

    private final String productSku;
    private final int requestedQuantity;
    private final int availableQuantity;

    public InsufficientStockException(String productSku, int requestedQuantity, int availableQuantity) {
        super(String.format("Insufficient stock for product %s. Requested: %d, Available: %d",
                productSku, requestedQuantity, availableQuantity));
        this.productSku = productSku;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String getProductSku() {
        return productSku;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
