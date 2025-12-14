package com.ecommerce.application.dto.cart;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for cart response.
 */
public class CartDto {

    private String id;
    private List<CartItemDto> items;
    private String discountCode;
    private BigDecimal discountAmount;
    private BigDecimal subtotal;
    private BigDecimal total;
    private int itemCount;

    public CartDto() {
    }

    public CartDto(String id, List<CartItemDto> items, String discountCode, BigDecimal discountAmount,
            BigDecimal subtotal, BigDecimal total, int itemCount) {
        this.id = id;
        this.items = items;
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.subtotal = subtotal;
        this.total = total;
        this.itemCount = itemCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public static class CartItemDto {
        private String id;
        private String productId;
        private String productName;
        private String productSlug;
        private String productImage;
        private String productSku;
        private BigDecimal unitPrice;
        private Integer quantity;
        private BigDecimal total;
        private boolean inStock;
        private int availableQuantity;

        public CartItemDto() {
        }

        public CartItemDto(String id, String productId, String productName, String productSlug,
                String productImage, String productSku, BigDecimal unitPrice,
                Integer quantity, BigDecimal total, boolean inStock, int availableQuantity) {
            this.id = id;
            this.productId = productId;
            this.productName = productName;
            this.productSlug = productSlug;
            this.productImage = productImage;
            this.productSku = productSku;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
            this.total = total;
            this.inStock = inStock;
            this.availableQuantity = availableQuantity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductSlug() {
            return productSlug;
        }

        public void setProductSlug(String productSlug) {
            this.productSlug = productSlug;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductSku() {
            return productSku;
        }

        public void setProductSku(String productSku) {
            this.productSku = productSku;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }

        public boolean isInStock() {
            return inStock;
        }

        public void setInStock(boolean inStock) {
            this.inStock = inStock;
        }

        public int getAvailableQuantity() {
            return availableQuantity;
        }

        public void setAvailableQuantity(int availableQuantity) {
            this.availableQuantity = availableQuantity;
        }
    }
}
