package com.aryahamrah.app.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository = null;

    public String save(Product product) {
        return productRepository.save(product).getId();
    }

    public Product findById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
