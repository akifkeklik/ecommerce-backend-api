package com.ecommerce.infrastructure.repository;

import com.ecommerce.domain.payment.Payment;
import com.ecommerce.domain.payment.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Payment entity operations.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    Optional<Payment> findByOrderId(UUID orderId);

    Optional<Payment> findByTransactionId(String transactionId);

    Optional<Payment> findByPaymentIntentId(String paymentIntentId);

    Page<Payment> findByStatus(PaymentStatus status, Pageable pageable);

    Optional<Payment> findByProviderTransactionId(String providerTransactionId);
}
