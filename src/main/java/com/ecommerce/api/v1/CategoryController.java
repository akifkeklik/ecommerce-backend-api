package com.ecommerce.api.v1;

import com.ecommerce.domain.product.Category;
import com.ecommerce.infrastructure.repository.CategoryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Category controller for product category operations.
 */
@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "Categories", description = "Product category management")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    @Operation(summary = "Get all active categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<Category> categories = categoryRepository.findAllActive();
        return ResponseEntity.ok(categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/tree")
    @Operation(summary = "Get category tree (root categories with children)")
    public ResponseEntity<List<CategoryDto>> getCategoryTree() {
        List<Category> rootCategories = categoryRepository.findActiveRootCategories();
        return ResponseEntity.ok(rootCategories.stream()
                .map(this::toDtoWithChildren)
                .collect(Collectors.toList()));
    }

    @GetMapping("/featured")
    @Operation(summary = "Get featured categories")
    public ResponseEntity<List<CategoryDto>> getFeaturedCategories() {
        List<Category> categories = categoryRepository.findFeaturedCategories();
        return ResponseEntity.ok(categories.stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Get category by slug")
    public ResponseEntity<CategoryDto> getCategoryBySlug(@PathVariable String slug) {
        return categoryRepository.findBySlug(slug)
                .map(this::toDtoWithChildren)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId().toString());
        dto.setName(category.getName());
        dto.setSlug(category.getSlug());
        dto.setDescription(category.getDescription());
        dto.setImageUrl(category.getImageUrl());
        dto.setParentId(category.getParent() != null ? category.getParent().getId().toString() : null);
        dto.setProductCount(category.getProducts().size());
        dto.setFeatured(category.isFeatured());
        return dto;
    }

    private CategoryDto toDtoWithChildren(Category category) {
        CategoryDto dto = toDto(category);
        dto.setChildren(category.getChildren().stream()
                .filter(Category::isActive)
                .map(this::toDtoWithChildren)
                .collect(Collectors.toList()));
        return dto;
    }

    public static class CategoryDto {
        private String id;
        private String name;
        private String slug;
        private String description;
        private String imageUrl;
        private String parentId;
        private int productCount;
        private boolean featured;
        private List<CategoryDto> children;

        public CategoryDto() {
        }

        public CategoryDto(String id, String name, String slug, String description, String imageUrl,
                String parentId, int productCount, boolean featured, List<CategoryDto> children) {
            this.id = id;
            this.name = name;
            this.slug = slug;
            this.description = description;
            this.imageUrl = imageUrl;
            this.parentId = parentId;
            this.productCount = productCount;
            this.featured = featured;
            this.children = children;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public int getProductCount() {
            return productCount;
        }

        public void setProductCount(int productCount) {
            this.productCount = productCount;
        }

        public boolean isFeatured() {
            return featured;
        }

        public void setFeatured(boolean featured) {
            this.featured = featured;
        }

        public List<CategoryDto> getChildren() {
            return children;
        }

        public void setChildren(List<CategoryDto> children) {
            this.children = children;
        }
    }
}
