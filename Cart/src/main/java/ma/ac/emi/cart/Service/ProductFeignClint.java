package ma.ac.emi.cart.Service;

import ma.ac.emi.cart.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "Product-service")
public interface ProductFeignClint {
   @GetMapping("/api/products/{id}")
   ProductDTO getProductById(@PathVariable Long id);


}
