package com.coughy.maybe.service;

import com.coughy.maybe.entity.Product;
import com.coughy.maybe.repository.ProductRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) throws DataIntegrityViolationException {
        if (product.getId() == null) {
            product.setVisible(false);
        }
        return productRepository.save(product);
    }

    public Set<Product> getAllByVisibleTrue() {
        return new HashSet<>(productRepository.getAllByVisibleTrue());
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Product toggle(String id) {
        Product product = productRepository.findById(id).get();
        product.setVisible(!product.getVisible());
        return productRepository.save(product);
    }
}
