package com.qima.qima.repository;


import com.qima.qima.domain.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("""
        SELECT p FROM Product p
        WHERE (:categoryId IS NULL OR p.category.id = :categoryId)
        AND (:id IS NULL OR p.id = :id)
    """)
    Page<Product> findProducts(
            @Param("id") Integer id,
            @Param("categoryId") Integer categoryId,
            Pageable pageable
    );

    @Modifying
    @Transactional
    @Query("""
        UPDATE Product p SET
          p.name = COALESCE(:name, p.name),
          p.description = COALESCE(:description, p.description),
          p.price = COALESCE(:price, p.price),
          p.available = COALESCE(:available, p.available)
        WHERE p.id = :id
    """)
    Integer update(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("description") String description,
            @Param("price") BigDecimal price,
            @Param("available") Boolean available
    );
}

