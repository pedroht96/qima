package com.qima.qima.service;


import com.qima.qima.domain.Category;
import com.qima.qima.domain.Product;
import com.qima.qima.repository.CategoryRepository;
import com.qima.qima.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Product save(Product product) {
        var idCategory = product.getCategory().getId();
        categoryRepository.findById(idCategory)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        return this.productRepository.save(product);
    }
    public Optional<Product> findById(Integer id){
        return this.productRepository.findById(id);
    }

    public void delete(Integer Id) {
        var product = this.productRepository.findById(Id);
        product.ifPresent(this.productRepository::delete);
    }

    public Page<Product> products(Integer id, Integer idCategory, Pageable pageable) {
        return this.productRepository.findProducts(id,idCategory, pageable);
    }

    public Integer update(Product product) {

        var id = product.getId();
        var name = product.getName();
        var description = product.getDescription();
        var price = product.getPrice();
        var available = product.getAvailable();

        return this.productRepository.update(id, name, description, price,available);
    }

    public List<Category> categories(){
        return this.categoryRepository.findAll();
    }
}
