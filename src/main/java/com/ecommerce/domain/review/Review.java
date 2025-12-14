package com.ecommerce.domain.review;

import com.ecommerce.domain.common.BaseEntity;
import com.ecommerce.domain.product.Product;
import com.ecommerce.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

/**
 * Review entity for product reviews and ratings.
 */
@Entity
@Table(name = "reviews", indexes = {
        @Index(name = "idx_review_product", columnList = "product_id"),
        @Index(name = "idx_review_user", columnList = "user_id"),
        @Index(name = "idx_review_rating", columnList = "rating")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uk_review_user_product", columnNames = { "user_id", "product_id" })
})
public class Review extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Size(max = 200)
    @Column(name = "title")
    private String title;

    @Size(max = 2000)
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "is_verified_purchase")
    private boolean verifiedPurchase = false;

    @Column(name = "is_approved")
    private boolean approved = false;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "helpful_count")
    private Integer helpfulCount = 0;

    @Column(name = "not_helpful_count")
    private Integer notHelpfulCount = 0;

    @Column(name = "is_featured")
    private boolean featured = false;

    @Column(name = "admin_response", columnDefinition = "TEXT")
    private String adminResponse;

    @Column(name = "admin_response_at")
    private LocalDateTime adminResponseAt;

    // ================== Constructors ==================

    public Review() {
        this.verifiedPurchase = false;
        this.approved = false;
        this.helpfulCount = 0;
        this.notHelpfulCount = 0;
        this.featured = false;
    }

    public Review(Product product, User user, Integer rating, String title, String comment,
            boolean verifiedPurchase, boolean approved, LocalDateTime approvedAt,
            String approvedBy, Integer helpfulCount, Integer notHelpfulCount,
            boolean featured, String adminResponse, LocalDateTime adminResponseAt) {
        this.product = product;
        this.user = user;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
        this.verifiedPurchase = verifiedPurchase;
        this.approved = approved;
        this.approvedAt = approvedAt;
        this.approvedBy = approvedBy;
        this.helpfulCount = helpfulCount != null ? helpfulCount : 0;
        this.notHelpfulCount = notHelpfulCount != null ? notHelpfulCount : 0;
        this.featured = featured;
        this.adminResponse = adminResponse;
        this.adminResponseAt = adminResponseAt;
    }

    // ================== Getters and Setters ==================

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isVerifiedPurchase() {
        return verifiedPurchase;
    }

    public void setVerifiedPurchase(boolean verifiedPurchase) {
        this.verifiedPurchase = verifiedPurchase;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Integer getHelpfulCount() {
        return helpfulCount;
    }

    public void setHelpfulCount(Integer helpfulCount) {
        this.helpfulCount = helpfulCount;
    }

    public Integer getNotHelpfulCount() {
        return notHelpfulCount;
    }

    public void setNotHelpfulCount(Integer notHelpfulCount) {
        this.notHelpfulCount = notHelpfulCount;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public String getAdminResponse() {
        return adminResponse;
    }

    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }

    public LocalDateTime getAdminResponseAt() {
        return adminResponseAt;
    }

    public void setAdminResponseAt(LocalDateTime adminResponseAt) {
        this.adminResponseAt = adminResponseAt;
    }

    // ================== Business Methods ==================

    /**
     * Approves the review for public display.
     */
    public void approve(String approvedBy) {
        this.approved = true;
        this.approvedAt = LocalDateTime.now();
        this.approvedBy = approvedBy;
    }

    /**
     * Rejects/hides the review.
     */
    public void reject() {
        this.approved = false;
    }

    /**
     * Marks the review as helpful.
     */
    public void markHelpful() {
        this.helpfulCount++;
    }

    /**
     * Marks the review as not helpful.
     */
    public void markNotHelpful() {
        this.notHelpfulCount++;
    }

    /**
     * Adds an admin response to the review.
     */
    public void addAdminResponse(String response) {
        this.adminResponse = response;
        this.adminResponseAt = LocalDateTime.now();
    }

    /**
     * Returns the helpfulness score.
     */
    public double getHelpfulnessScore() {
        int total = helpfulCount + notHelpfulCount;
        if (total == 0)
            return 0;
        return (double) helpfulCount / total;
    }
}
