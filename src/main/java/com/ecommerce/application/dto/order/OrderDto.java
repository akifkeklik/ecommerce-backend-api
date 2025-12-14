package com.ecommerce.application.dto.order;

import com.ecommerce.application.dto.common.AddressDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for order response.
 */
public class OrderDto {

    private String id;
    private String orderNumber;
    private String status;
    private List<OrderItemDto> items;
    private AddressDto shippingAddress;
    private AddressDto billingAddress;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;
    private String discountCode;
    private BigDecimal taxAmount;
    private BigDecimal shippingAmount;
    private BigDecimal total;
    private String currency;
    private PaymentDto payment;
    private ShipmentDto shipment;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;
    private int itemCount;

    public OrderDto() {
    }

    public OrderDto(String id, String orderNumber, String status, List<OrderItemDto> items,
            AddressDto shippingAddress, AddressDto billingAddress, BigDecimal subtotal,
            BigDecimal discountAmount, String discountCode, BigDecimal taxAmount,
            BigDecimal shippingAmount, BigDecimal total, String currency,
            PaymentDto payment, ShipmentDto shipment, String notes,
            LocalDateTime createdAt, LocalDateTime completedAt, int itemCount) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.items = items;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.subtotal = subtotal;
        this.discountAmount = discountAmount;
        this.discountCode = discountCode;
        this.taxAmount = taxAmount;
        this.shippingAmount = shippingAmount;
        this.total = total;
        this.currency = currency;
        this.payment = payment;
        this.shipment = shipment;
        this.notes = notes;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.itemCount = itemCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public AddressDto getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDto shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public AddressDto getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDto billingAddress) {
        this.billingAddress = billingAddress;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public void setShippingAmount(BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

    public ShipmentDto getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentDto shipment) {
        this.shipment = shipment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public static class OrderItemDto {
        private String id;
        private String productId;
        private String productName;
        private String productSku;
        private String productImage;
        private Integer quantity;
        private BigDecimal unitPrice;
        private BigDecimal discountAmount;
        private BigDecimal total;

        public OrderItemDto() {
        }

        public OrderItemDto(String id, String productId, String productName, String productSku,
                String productImage, Integer quantity, BigDecimal unitPrice,
                BigDecimal discountAmount, BigDecimal total) {
            this.id = id;
            this.productId = productId;
            this.productName = productName;
            this.productSku = productSku;
            this.productImage = productImage;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.discountAmount = discountAmount;
            this.total = total;
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

        public String getProductSku() {
            return productSku;
        }

        public void setProductSku(String productSku) {
            this.productSku = productSku;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public BigDecimal getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
        }

        public BigDecimal getTotal() {
            return total;
        }

        public void setTotal(BigDecimal total) {
            this.total = total;
        }
    }

    public static class PaymentDto {
        private String id;
        private String status;
        private String method;
        private BigDecimal amount;
        private String transactionId;
        private String cardLastFour;
        private String cardBrand;
        private LocalDateTime processedAt;

        public PaymentDto() {
        }

        public PaymentDto(String id, String status, String method, BigDecimal amount,
                String transactionId, String cardLastFour, String cardBrand,
                LocalDateTime processedAt) {
            this.id = id;
            this.status = status;
            this.method = method;
            this.amount = amount;
            this.transactionId = transactionId;
            this.cardLastFour = cardLastFour;
            this.cardBrand = cardBrand;
            this.processedAt = processedAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getCardLastFour() {
            return cardLastFour;
        }

        public void setCardLastFour(String cardLastFour) {
            this.cardLastFour = cardLastFour;
        }

        public String getCardBrand() {
            return cardBrand;
        }

        public void setCardBrand(String cardBrand) {
            this.cardBrand = cardBrand;
        }

        public LocalDateTime getProcessedAt() {
            return processedAt;
        }

        public void setProcessedAt(LocalDateTime processedAt) {
            this.processedAt = processedAt;
        }
    }

    public static class ShipmentDto {
        private String id;
        private String status;
        private String method;
        private String carrier;
        private String trackingNumber;
        private String trackingUrl;
        private LocalDateTime estimatedDelivery;
        private LocalDateTime shippedAt;
        private LocalDateTime deliveredAt;

        public ShipmentDto() {
        }

        public ShipmentDto(String id, String status, String method, String carrier,
                String trackingNumber, String trackingUrl, LocalDateTime estimatedDelivery,
                LocalDateTime shippedAt, LocalDateTime deliveredAt) {
            this.id = id;
            this.status = status;
            this.method = method;
            this.carrier = carrier;
            this.trackingNumber = trackingNumber;
            this.trackingUrl = trackingUrl;
            this.estimatedDelivery = estimatedDelivery;
            this.shippedAt = shippedAt;
            this.deliveredAt = deliveredAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getCarrier() {
            return carrier;
        }

        public void setCarrier(String carrier) {
            this.carrier = carrier;
        }

        public String getTrackingNumber() {
            return trackingNumber;
        }

        public void setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
        }

        public String getTrackingUrl() {
            return trackingUrl;
        }

        public void setTrackingUrl(String trackingUrl) {
            this.trackingUrl = trackingUrl;
        }

        public LocalDateTime getEstimatedDelivery() {
            return estimatedDelivery;
        }

        public void setEstimatedDelivery(LocalDateTime estimatedDelivery) {
            this.estimatedDelivery = estimatedDelivery;
        }

        public LocalDateTime getShippedAt() {
            return shippedAt;
        }

        public void setShippedAt(LocalDateTime shippedAt) {
            this.shippedAt = shippedAt;
        }

        public LocalDateTime getDeliveredAt() {
            return deliveredAt;
        }

        public void setDeliveredAt(LocalDateTime deliveredAt) {
            this.deliveredAt = deliveredAt;
        }
    }
}
