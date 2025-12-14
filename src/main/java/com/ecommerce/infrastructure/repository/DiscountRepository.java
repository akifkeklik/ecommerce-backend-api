package com.ecommerce.infrastructure.repository;

import com.ecommerce.domain.promotion.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for Discount entity operations.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, UUID> {

    Optional<Discount> findByCode(String code);

    boolean existsByCode(String code);

    @Query("SELECT d FROM Discount d WHERE d.active = true AND (d.startDate IS NULL OR d.startDate <= :now) AND (d.endDate IS NULL OR d.endDate >= :now)")
    List<Discount> findActiveDiscounts(LocalDateTime now);

    @Query("SELECT d FROM Discount d WHERE d.code = :code AND d.active = true AND (d.startDate IS NULL OR d.startDate <= :now) AND (d.endDate IS NULL OR d.endDate >= :now)")
    Optional<Discount> findValidDiscount(String code, LocalDateTime now);
}
