package com.avenue.com.avenue.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avenue.com.avenue.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByParentProduct_Id(Long parentProdutId);

}
