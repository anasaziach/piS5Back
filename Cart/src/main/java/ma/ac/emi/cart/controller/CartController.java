package ma.ac.emi.cart.controller;

import ma.ac.emi.cart.Service.CartService;
import ma.ac.emi.cart.dto.CartDTO;
import ma.ac.emi.cart.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartDTO> createCart(@RequestBody Cart cart) {
        Cart createdCart = cartService.createCart(cart);
        CartDTO cartDTO = cartService.convertToDTO(createdCart);
        return ResponseEntity.ok(cartDTO);
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<Cart> carts = cartService.getAllCarts();
        List<CartDTO> cartDTOs = carts.stream()
                .map(cartService::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long id) {
        Optional<Cart> cartOptional = cartService.getCartById(id);
        return cartOptional.map(cart -> ResponseEntity.ok(cartService.convertToDTO(cart)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart updatedCartEntity = cartService.updateCart(id, updatedCart);
        if (updatedCartEntity != null) {
            return ResponseEntity.ok(cartService.convertToDTO(updatedCartEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<CartDTO> addProductId(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart updatedCart = cartService.addProductId(cartId, productId);
        if (updatedCart != null) {
            return ResponseEntity.ok(cartService.convertToDTO(updatedCart));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<CartDTO> removeProductId(@PathVariable Long cartId, @PathVariable Long productId) {
        Cart updatedCart = cartService.removeProductId(cartId, productId);
        if (updatedCart != null) {
            return ResponseEntity.ok(cartService.convertToDTO(updatedCart));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
