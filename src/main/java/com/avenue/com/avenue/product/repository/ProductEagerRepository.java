package com.avenue.com.avenue.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avenue.com.avenue.product.entity.Product;

@Repository
public interface ProductEagerRepository extends JpaRepository<Product, Long> {

    @EntityGraph(value = "Product.eager", type = EntityGraph.EntityGraphType.LOAD)
    @Override
    List<Product> findAll();
    
    @EntityGraph(value = "Product.eager", type = EntityGraph.EntityGraphType.LOAD)
    @Override
    Optional<Product> findById(Long id);
}
