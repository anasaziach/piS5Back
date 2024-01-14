package ma.ac.emi.cart;

import ma.ac.emi.cart.entity.Cart;
import ma.ac.emi.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CartApplication implements CommandLineRunner {
    @Autowired
    CartRepository cartRepository;

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Long> listProductsId = new ArrayList<>();
        listProductsId.add(130L);
        cartRepository.save(new Cart(
                4L,
                listProductsId
        ));
    }
}



















