package com.qima.qima.controller;

import com.qima.qima.domain.Category;
import com.qima.qima.domain.Product;
import com.qima.qima.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<Product>> findProduct(@RequestParam(required = false) Integer id,
                                                     @RequestParam(required = false) Integer idCategory,
                                                     @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        var pagination = this.service.products(id,idCategory, pageable);

        return  ResponseEntity.status(HttpStatus.OK).body(pagination);
    }


    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
        var post = this.service.save(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        this.service.delete(id);
    }

    @PutMapping
    public ResponseEntity<Product> update(@RequestBody @Valid Product product) {
        this.service.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

       this.service.update(product);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> categories() {
        var categories = this.service.categories();
        return ResponseEntity.ok(categories);
    }
}
