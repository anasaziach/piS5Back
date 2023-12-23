package ma.ac.emi.cart.repository;

import ma.ac.emi.cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom query methods if needed
}
