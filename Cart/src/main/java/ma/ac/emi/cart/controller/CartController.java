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
@CrossOrigin("*")
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public Cart createCart(@RequestBody CartDTO cart) {
        return cartService.createCart(cart);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getAllCartsByUserId(@PathVariable("userId") Long id) {
        return cartService.getAllCartsByUserId(id);
    }


    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }

    @PostMapping("/{cartId}/add/{productId}")
    public Cart addProductId(@PathVariable Long cartId, @PathVariable Long productId) {
        return cartService.addProductId(cartId,productId);
    }

    @PostMapping("/{cartId}/remove/{productId}")
    public Cart removeProductId(@PathVariable Long cartId, @PathVariable Long productId) {
        return cartService.removeProductId(cartId,productId);
    }
}
