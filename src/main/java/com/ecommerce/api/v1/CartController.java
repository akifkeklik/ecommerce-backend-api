package com.ecommerce.api.v1;

import com.ecommerce.application.dto.cart.AddToCartRequest;
import com.ecommerce.application.dto.cart.CartDto;
import com.ecommerce.application.service.CartService;
import com.ecommerce.domain.user.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Cart controller for shopping cart operations.
 */
@RestController
@RequestMapping("/api/v1/cart")
@Tag(name = "Shopping Cart", description = "Shopping cart operations")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Operation(summary = "Get current user's cart")
    public ResponseEntity<CartDto> getCart(
            @AuthenticationPrincipal User user,
            HttpSession session) {

        if (user != null) {
            return ResponseEntity.ok(cartService.getCart(user));
        }
        return ResponseEntity.ok(cartService.getCartBySession(session.getId()));
    }

    @PostMapping("/items")
    @Operation(summary = "Add item to cart")
    public ResponseEntity<CartDto> addToCart(
            @Valid @RequestBody AddToCartRequest request,
            @AuthenticationPrincipal User user,
            HttpSession session) {

        if (user != null) {
            return ResponseEntity.ok(cartService.addToCart(user, request));
        }
        return ResponseEntity.ok(cartService.addToCartBySession(session.getId(), request));
    }

    @PutMapping("/items/{itemId}")
    @Operation(summary = "Update cart item quantity")
    public ResponseEntity<CartDto> updateCartItem(
            @PathVariable String itemId,
            @RequestParam int quantity,
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(cartService.updateCartItem(user, itemId, quantity));
    }

    @DeleteMapping("/items/{itemId}")
    @Operation(summary = "Remove item from cart")
    public ResponseEntity<CartDto> removeFromCart(
            @PathVariable String itemId,
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(cartService.removeFromCart(user, itemId));
    }

    @DeleteMapping
    @Operation(summary = "Clear entire cart")
    public ResponseEntity<Void> clearCart(@AuthenticationPrincipal User user) {
        cartService.clearCart(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/merge")
    @Operation(summary = "Merge guest cart into user cart after login")
    public ResponseEntity<CartDto> mergeCart(
            @AuthenticationPrincipal User user,
            HttpSession session) {

        return ResponseEntity.ok(cartService.mergeCart(user, session.getId()));
    }
}
