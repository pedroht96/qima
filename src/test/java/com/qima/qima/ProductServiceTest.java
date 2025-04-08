package com.qima.qima;

import com.qima.qima.domain.Product;
import com.qima.qima.repository.ProductRepository;
import com.qima.qima.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1);
        product.setName("Produto Teste");
        product.setPrice(BigDecimal.valueOf(10.00));
        product.setAvailable(true);
        product.setDescription("Descrição");
    }

    @Test
    void shouldSaveProduct() {
        when(repository.save(product)).thenReturn(product);
        Product saved = service.save(product);
        assertEquals("Produto Teste", saved.getName());
        verify(repository).save(product);
    }

    @Test
    void shouldFindProductById() {
        when(repository.findById(1)).thenReturn(Optional.of(product));
        Optional<Product> result = service.findById(1);
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void shouldDeleteProductIfExists() {
        when(repository.findById(1)).thenReturn(Optional.of(product));
        service.delete(1);
        verify(repository).delete(product);
    }

    @Test
    void shouldNotDeleteIfProductNotFound() {
        when(repository.findById(99)).thenReturn(Optional.empty());
        service.delete(99);
        verify(repository, never()).delete(any());
    }

    @Test
    void shouldReturnPagedProducts() {
        var page = new PageImpl<>(List.of(product));
        var pageable = PageRequest.of(0, 10);
        when(repository.findProducts(null, null, pageable)).thenReturn(page);

        var result = service.products(null, null, pageable);
        assertEquals(1, result.getTotalElements());
        verify(repository).findProducts(null, null, pageable);
    }

    @Test
    void shouldUpdateProduct() {
        when(repository.update(
                eq(1),
                eq("Produto Teste"),
                eq("Descrição"),
                eq(BigDecimal.valueOf(10.00)),
                eq(true)
        )).thenReturn(1);

        int updated = service.update(product);
        assertEquals(1, updated);
        verify(repository).update(any(), any(), any(), any(), any());
    }
}
