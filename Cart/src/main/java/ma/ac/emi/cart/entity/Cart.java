package ma.ac.emi.cart.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    @ElementCollection
    private List<Long> productIds;
    public void addProductToCart(Long productId) {
        productIds.add(productId);
    }

    public Cart(Long userId, List<Long> productIds) {
        this.userId = userId;
        this.productIds = productIds;
    }
}