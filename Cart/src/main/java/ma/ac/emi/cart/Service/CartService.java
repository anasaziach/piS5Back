package ma.ac.emi.cart.Service;

import ma.ac.emi.cart.dto.CartDTO;
import ma.ac.emi.cart.entity.Cart;
import ma.ac.emi.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(Long cartId) {
        return cartRepository.findById(cartId);
    }

    public Cart updateCart(Long cartId, Cart updatedCart) {
        Optional<Cart> existingCartOptional = cartRepository.findById(cartId);

        if (existingCartOptional.isPresent()) {
            Cart existingCart = existingCartOptional.get();
            // Update properties of the existing cart with those from updatedCart
            existingCart.setProductIds(updatedCart.getProductIds());
            // You can update other properties as needed

            return cartRepository.save(existingCart);
        } else {
            // Handle cart not found
            return null;
        }
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
    public Cart addProductId(Long cartId, Long productId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<Long> productIds = cart.getProductIds();

            // Add the new productId
            productIds.add(productId);

            // Update the cart with the modified productIds
            cart.setProductIds(productIds);

            return cartRepository.save(cart);
        } else {
            // Handle cart not found
            return null;
        }
    }

    public Cart removeProductId(Long cartId, Long productId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);

        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<Long> productIds = cart.getProductIds();

            // Remove the productId
            productIds.remove(productId);

            // Update the cart with the modified productIds
            cart.setProductIds(productIds);

            return cartRepository.save(cart);
        } else {
            // Handle cart not found
            return null;
        }
    }
    public CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setProductIds(cart.getProductIds());
        // Set other fields as needed
        return cartDTO;
    }
}

