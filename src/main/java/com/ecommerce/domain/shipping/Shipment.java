package com.ecommerce.domain.shipping;

import com.ecommerce.domain.common.BaseEntity;
import com.ecommerce.domain.order.Order;
import com.ecommerce.domain.user.Address;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Shipment entity for tracking order deliveries.
 */
@Entity
@Table(name = "shipments", indexes = {
        @Index(name = "idx_shipment_order", columnList = "order_id"),
        @Index(name = "idx_shipment_tracking", columnList = "tracking_number"),
        @Index(name = "idx_shipment_status", columnList = "status")
})
public class Shipment extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "method", nullable = false)
    private ShippingMethod method;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ShipmentStatus status = ShipmentStatus.PENDING;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "tracking_url")
    private String trackingUrl;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "ship_to_street")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "ship_to_address_line2")),
            @AttributeOverride(name = "city", column = @Column(name = "ship_to_city")),
            @AttributeOverride(name = "state", column = @Column(name = "ship_to_state")),
            @AttributeOverride(name = "postalCode", column = @Column(name = "ship_to_postal_code")),
            @AttributeOverride(name = "country", column = @Column(name = "ship_to_country")),
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "ship_to_phone"))
    })
    private Address shippingAddress;

    @Column(name = "shipping_cost", precision = 12, scale = 2)
    private BigDecimal shippingCost;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "estimated_delivery")
    private LocalDateTime estimatedDelivery;

    @Column(name = "shipped_at")
    private LocalDateTime shippedAt;

    @Column(name = "delivered_at")
    private LocalDateTime deliveredAt;

    @Column(name = "delivery_instructions", columnDefinition = "TEXT")
    private String deliveryInstructions;

    @Column(name = "signature_required")
    private boolean signatureRequired = false;

    @Column(name = "signed_by")
    private String signedBy;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public Shipment() {
        this.status = ShipmentStatus.PENDING;
        this.signatureRequired = false;
    }

    // Getters and Setters
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ShippingMethod getMethod() {
        return method;
    }

    public void setMethod(ShippingMethod method) {
        this.method = method;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
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

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BigDecimal getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public boolean isSignatureRequired() {
        return signatureRequired;
    }

    public void setSignatureRequired(boolean signatureRequired) {
        this.signatureRequired = signatureRequired;
    }

    public String getSignedBy() {
        return signedBy;
    }

    public void setSignedBy(String signedBy) {
        this.signedBy = signedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Business Methods
    public void ship(String carrier, String trackingNumber) {
        this.carrier = carrier;
        this.trackingNumber = trackingNumber;
        this.status = ShipmentStatus.SHIPPED;
        this.shippedAt = LocalDateTime.now();
    }

    public void updateStatus(ShipmentStatus newStatus) {
        this.status = newStatus;
        if (newStatus == ShipmentStatus.DELIVERED) {
            this.deliveredAt = LocalDateTime.now();
        }
    }

    public void deliver(String signedBy) {
        this.status = ShipmentStatus.DELIVERED;
        this.deliveredAt = LocalDateTime.now();
        if (signatureRequired && signedBy != null) {
            this.signedBy = signedBy;
        }
    }

    public boolean isTrackable() {
        return trackingNumber != null && !trackingNumber.isBlank();
    }

    public String getFullTrackingUrl() {
        if (trackingUrl != null) {
            return trackingUrl;
        }
        if (carrier != null && trackingNumber != null) {
            return switch (carrier.toUpperCase()) {
                case "UPS" -> "https://www.ups.com/track?tracknum=" + trackingNumber;
                case "FEDEX" -> "https://www.fedex.com/fedextrack/?tracknumbers=" + trackingNumber;
                case "USPS" -> "https://tools.usps.com/go/TrackConfirmAction?tLabels=" + trackingNumber;
                case "DHL" -> "https://www.dhl.com/en/express/tracking.html?AWB=" + trackingNumber;
                default -> null;
            };
        }
        return null;
    }
}
