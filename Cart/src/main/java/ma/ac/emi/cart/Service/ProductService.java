package ma.ac.emi.cart.Service;

import ma.ac.emi.cart.dto.ProductDTO;
import ma.ac.emi.cart.entity.Product;
import ma.ac.emi.cart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        // Set other fields as needed
        return productDTO;
    }
}
