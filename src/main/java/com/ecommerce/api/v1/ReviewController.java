package com.ecommerce.api.v1;

import com.ecommerce.domain.review.Review;
import com.ecommerce.domain.user.User;
import com.ecommerce.infrastructure.repository.ProductRepository;
import com.ecommerce.infrastructure.repository.ReviewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Review controller for product reviews.
 */
@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Reviews", description = "Product reviews and ratings")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewController(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get reviews for a product")
    public ResponseEntity<PagedReviewResponse> getProductReviews(
            @PathVariable UUID productId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Review> reviews = reviewRepository.findByProductIdAndApproved(productId, true, pageable);

        PagedReviewResponse response = new PagedReviewResponse();
        response.setContent(reviews.getContent().stream().map(this::toDto).toList());
        response.setPage(reviews.getNumber());
        response.setSize(reviews.getSize());
        response.setTotalElements(reviews.getTotalElements());
        response.setTotalPages(reviews.getTotalPages());
        response.setAverageRating(reviewRepository.getAverageRatingByProductId(productId));
        response.setTotalReviews(reviewRepository.countApprovedByProductId(productId));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Submit a product review")
    public ResponseEntity<ReviewDto> createReview(
            @Valid @RequestBody CreateReviewRequest request,
            @AuthenticationPrincipal User user) {

        UUID productId = UUID.fromString(request.getProductId());

        // Check if user already reviewed this product
        if (reviewRepository.existsByProductIdAndUserId(productId, user.getId())) {
            return ResponseEntity.badRequest().build();
        }

        var product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Review review = new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setRating(request.getRating());
        review.setTitle(request.getTitle());
        review.setComment(request.getComment());
        review.setVerifiedPurchase(false); // Would check order history
        review.setApproved(false); // Requires moderation

        review = reviewRepository.save(review);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(review));
    }

    @PostMapping("/{id}/helpful")
    @Operation(summary = "Mark a review as helpful")
    public ResponseEntity<Void> markHelpful(@PathVariable UUID id) {
        reviewRepository.findById(id).ifPresent(review -> {
            review.markHelpful();
            reviewRepository.save(review);
        });
        return ResponseEntity.ok().build();
    }

    private ReviewDto toDto(Review review) {
        ReviewDto dto = new ReviewDto();
        dto.setId(review.getId().toString());
        dto.setRating(review.getRating());
        dto.setTitle(review.getTitle());
        dto.setComment(review.getComment());
        dto.setAuthor(review.getUser().getUsername());
        dto.setAuthorAvatar(review.getUser().getAvatarUrl());
        dto.setVerifiedPurchase(review.isVerifiedPurchase());
        dto.setHelpfulCount(review.getHelpfulCount());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setAdminResponse(review.getAdminResponse());
        return dto;
    }

    public static class ReviewDto {
        private String id;
        private Integer rating;
        private String title;
        private String comment;
        private String author;
        private String authorAvatar;
        private boolean verifiedPurchase;
        private Integer helpfulCount;
        private LocalDateTime createdAt;
        private String adminResponse;

        public ReviewDto() {
        }

        public ReviewDto(String id, Integer rating, String title, String comment, String author,
                String authorAvatar, boolean verifiedPurchase, Integer helpfulCount,
                LocalDateTime createdAt, String adminResponse) {
            this.id = id;
            this.rating = rating;
            this.title = title;
            this.comment = comment;
            this.author = author;
            this.authorAvatar = authorAvatar;
            this.verifiedPurchase = verifiedPurchase;
            this.helpfulCount = helpfulCount;
            this.createdAt = createdAt;
            this.adminResponse = adminResponse;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorAvatar() {
            return authorAvatar;
        }

        public void setAuthorAvatar(String authorAvatar) {
            this.authorAvatar = authorAvatar;
        }

        public boolean isVerifiedPurchase() {
            return verifiedPurchase;
        }

        public void setVerifiedPurchase(boolean verifiedPurchase) {
            this.verifiedPurchase = verifiedPurchase;
        }

        public Integer getHelpfulCount() {
            return helpfulCount;
        }

        public void setHelpfulCount(Integer helpfulCount) {
            this.helpfulCount = helpfulCount;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public String getAdminResponse() {
            return adminResponse;
        }

        public void setAdminResponse(String adminResponse) {
            this.adminResponse = adminResponse;
        }
    }

    public static class PagedReviewResponse {
        private List<ReviewDto> content;
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
        private Double averageRating;
        private Long totalReviews;

        public PagedReviewResponse() {
        }

        public PagedReviewResponse(List<ReviewDto> content, int page, int size, long totalElements,
                int totalPages, Double averageRating, Long totalReviews) {
            this.content = content;
            this.page = page;
            this.size = size;
            this.totalElements = totalElements;
            this.totalPages = totalPages;
            this.averageRating = averageRating;
            this.totalReviews = totalReviews;
        }

        public List<ReviewDto> getContent() {
            return content;
        }

        public void setContent(List<ReviewDto> content) {
            this.content = content;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public Double getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(Double averageRating) {
            this.averageRating = averageRating;
        }

        public Long getTotalReviews() {
            return totalReviews;
        }

        public void setTotalReviews(Long totalReviews) {
            this.totalReviews = totalReviews;
        }
    }

    public static class CreateReviewRequest {
        @NotNull
        private String productId;

        @NotNull
        @Min(1)
        @Max(5)
        private Integer rating;

        @Size(max = 200)
        private String title;

        @Size(max = 2000)
        private String comment;

        public CreateReviewRequest() {
        }

        public CreateReviewRequest(String productId, Integer rating, String title, String comment) {
            this.productId = productId;
            this.rating = rating;
            this.title = title;
            this.comment = comment;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
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
    }
}
