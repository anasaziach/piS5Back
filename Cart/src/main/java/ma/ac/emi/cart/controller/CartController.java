package ma.ac.emi.cart.controller;

import ma.ac.emi.cart.Service.CartService;
import ma.ac.emi.cart.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addToCart(@RequestBody ProductDTO productDTO) {
        ProductDTO addedProductDTO = cartService.addToCart(productDTO);
        return new ResponseEntity<>(addedProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public List<ProductDTO> getCartItems() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/remove/{productId}")
    public void removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
    }

    @GetMapping("/total")
    public double getCartTotal() {
        return cartService.getCartTotal();
    }
}
