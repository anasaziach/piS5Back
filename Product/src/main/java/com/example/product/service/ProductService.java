package com.example.product.service;

import com.example.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO productDTO);
    List<ProductDTO> getAllProduct();
    ProductDTO getProductById(Long id);
    ProductDTO updateProduct(Long id,ProductDTO updatedProduct);
    void deleteProduct(Long id);
}
