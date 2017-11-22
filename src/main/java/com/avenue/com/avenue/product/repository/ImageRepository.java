package com.avenue.com.avenue.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avenue.com.avenue.product.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

	List<Image> findByProduct_Id(Long id);
}
