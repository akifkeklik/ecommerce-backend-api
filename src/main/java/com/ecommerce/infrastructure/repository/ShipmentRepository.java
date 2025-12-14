package com.ecommerce.infrastructure.repository;

import com.ecommerce.domain.shipping.Shipment;
import com.ecommerce.domain.shipping.ShipmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Shipment entity operations.
 */
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

    Optional<Shipment> findByOrderId(UUID orderId);

    Optional<Shipment> findByTrackingNumber(String trackingNumber);

    Page<Shipment> findByStatus(ShipmentStatus status, Pageable pageable);
}
