package ma.ac.emi.cart.Service;

import ma.ac.emi.cart.dto.CartDTO;
import ma.ac.emi.cart.dto.ProductDTO;
import ma.ac.emi.cart.entity.Cart;
import ma.ac.emi.cart.entity.Product;
import ma.ac.emi.cart.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CartService {
    private Cart cart;
    private ProductRepository productRepository;

    public CartService() {
        this.cart = new Cart();
    }

    public ProductDTO addToCart(ProductDTO productDTO) {
        // Convert ProductDTO to Product
        Product product = convertToProduct(productDTO);

        // Set the bidirectional relationship
        product.setCart(cart);
        cart.getItems().add(product);

        // Save changes to the database, if necessary

        // Convert the added product back to ProductDTO
        return convertToProductDTO(product);
    }

    public void removeFromCart(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        productOptional.ifPresent(product -> {
            cart.getItems().remove(product);
            product.setCart(null);
        });
    }
    public double getCartTotal() {
        return cart.calculateTotal();
    }
    public List<ProductDTO> getCartItems() {
        return cart.getItems().stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    public CartDTO convertToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        // Set other fields as needed
        return cartDTO;
    }
    private Product convertToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        // Set other fields as needed
        return product;
    }
    private ProductDTO convertToProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        // Set other fields as needed
        return productDTO;
    }

}
