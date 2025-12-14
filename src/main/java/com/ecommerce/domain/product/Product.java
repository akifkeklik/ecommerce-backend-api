package com.ecommerce.domain.product;

import com.ecommerce.domain.common.BaseEntity;
import com.ecommerce.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Product entity representing items available for sale.
 */
@Entity
@Table(name = "products", indexes = {
        @Index(name = "idx_product_sku", columnList = "sku", unique = true),
        @Index(name = "idx_product_slug", columnList = "slug", unique = true),
        @Index(name = "idx_product_category", columnList = "category_id"),
        @Index(name = "idx_product_seller", columnList = "seller_id"),
        @Index(name = "idx_product_status", columnList = "status"),
        @Index(name = "idx_product_price", columnList = "price")
})
public class Product extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Size(max = 280)
    @Column(name = "slug", unique = true, nullable = false)
    private String slug;

    @NotBlank
    @Size(max = 50)
    @Column(name = "sku", unique = true, nullable = false)
    private String sku;

    @Size(max = 500)
    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @DecimalMin(value = "0.01")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "price", nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @DecimalMin(value = "0.00")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "compare_at_price", precision = 12, scale = 2)
    private BigDecimal compareAtPrice;

    @DecimalMin(value = "0.00")
    @Digits(integer = 10, fraction = 2)
    @Column(name = "cost_price", precision = 12, scale = 2)
    private BigDecimal costPrice;

    @NotNull
    @Min(0)
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Min(0)
    @Column(name = "low_stock_threshold")
    private Integer lowStockThreshold = 10;

    @Min(0)
    @Column(name = "reserved_quantity")
    private Integer reservedQuantity = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ProductStatus status = ProductStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC")
    private List<ProductImage> images = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "product_tags", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();

    @Column(name = "weight")
    private Double weight;

    @Column(name = "weight_unit")
    private String weightUnit = "kg";

    @Column(name = "length")
    private Double length;

    @Column(name = "width")
    private Double width;

    @Column(name = "height")
    private Double height;

    @Column(name = "dimension_unit")
    private String dimensionUnit = "cm";

    @Column(name = "average_rating", precision = 3, scale = 2)
    private BigDecimal averageRating = BigDecimal.ZERO;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @Column(name = "total_sales")
    private Integer totalSales = 0;

    @Column(name = "is_featured")
    private boolean featured = false;

    @Column(name = "is_digital")
    private boolean digital = false;

    @Column(name = "requires_shipping")
    private boolean requiresShipping = true;

    @Column(name = "meta_title")
    @Size(max = 70)
    private String metaTitle;

    @Column(name = "meta_description")
    @Size(max = 160)
    private String metaDescription;

    public Product() {
        this.stockQuantity = 0;
        this.lowStockThreshold = 10;
        this.reservedQuantity = 0;
        this.status = ProductStatus.DRAFT;
        this.images = new ArrayList<>();
        this.tags = new HashSet<>();
        this.weightUnit = "kg";
        this.dimensionUnit = "cm";
        this.averageRating = BigDecimal.ZERO;
        this.reviewCount = 0;
        this.totalSales = 0;
        this.featured = false;
        this.digital = false;
        this.requiresShipping = true;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCompareAtPrice() {
        return compareAtPrice;
    }

    public void setCompareAtPrice(BigDecimal compareAtPrice) {
        this.compareAtPrice = compareAtPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    public Integer getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getDimensionUnit() {
        return dimensionUnit;
    }

    public void setDimensionUnit(String dimensionUnit) {
        this.dimensionUnit = dimensionUnit;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Integer totalSales) {
        this.totalSales = totalSales;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public boolean isRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    // ================== Business Methods ==================

    public int getAvailableQuantity() {
        return stockQuantity - reservedQuantity;
    }

    public boolean isInStock() {
        return getAvailableQuantity() > 0;
    }

    public boolean isLowStock() {
        return getAvailableQuantity() <= lowStockThreshold;
    }

    public boolean isAvailable() {
        return status == ProductStatus.ACTIVE && isInStock() && !isDeleted();
    }

    public void reserveStock(int quantity) {
        if (quantity > getAvailableQuantity()) {
            throw new IllegalStateException("Insufficient stock available");
        }
        this.reservedQuantity += quantity;
    }

    public void releaseStock(int quantity) {
        this.reservedQuantity = Math.max(0, this.reservedQuantity - quantity);
    }

    public void confirmSale(int quantity) {
        this.stockQuantity -= quantity;
        this.reservedQuantity -= quantity;
        this.totalSales += quantity;
    }

    public String getPrimaryImageUrl() {
        return images.stream()
                .filter(ProductImage::isPrimary)
                .findFirst()
                .map(ProductImage::getUrl)
                .orElse(images.isEmpty() ? null : images.get(0).getUrl());
    }

    public int getDiscountPercentage() {
        if (compareAtPrice == null || compareAtPrice.compareTo(price) <= 0) {
            return 0;
        }
        return compareAtPrice.subtract(price)
                .divide(compareAtPrice, 2, java.math.RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .intValue();
    }

    public void addImage(ProductImage image) {
        images.add(image);
        image.setProduct(this);
        if (images.size() == 1) {
            image.setPrimary(true);
        }
    }

    public void removeImage(ProductImage image) {
        images.remove(image);
        image.setProduct(null);
    }

    public void updateRating(BigDecimal newRating) {
        BigDecimal totalRating = averageRating.multiply(BigDecimal.valueOf(reviewCount));
        reviewCount++;
        averageRating = totalRating.add(newRating)
                .divide(BigDecimal.valueOf(reviewCount), 2, java.math.RoundingMode.HALF_UP);
    }
}
