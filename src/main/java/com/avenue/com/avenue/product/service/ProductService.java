package com.avenue.com.avenue.product.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenue.com.avenue.product.entity.Product;
import com.avenue.com.avenue.product.model.ProductDTO;
import com.avenue.com.avenue.product.repository.ProductEagerRepository;
import com.avenue.com.avenue.product.repository.ProductRepository;
import com.avenue.com.avenue.product.util.converter.ProductConverter;

@Service
public class ProductService {

	@Autowired
	private ProductEagerRepository productEagerRepository;

	@Autowired
	private ProductRepository productRespository;

	public List<ProductDTO> findAllProducts() {
		return ProductConverter.convert(this.productRespository.findAll());
	}

	public ProductDTO getByProduct(Long id) {
		Product product = this.productRespository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Product " + id + " does not exist"));
		return ProductConverter.convert(product);
	}

	@Transactional
	public void createProduct(Product product) {
		checkIfExistProduct(product.getParentProduct());
		this.productRespository.save(product);
	}

	@Transactional
	public void updateProduct(Long id, Product product) {
		checkIfExistProduct(new Product(id));
		checkIfExistProduct(product.getParentProduct());

		product.setId(id);
		this.productRespository.save(product);
	}

	@Transactional
	public void deleteProduct(Long id) {
		this.productRespository.deleteById(id);
	}

	public List<ProductDTO> findAllProductsIncludingRelationships() {
		return ProductConverter.convert(this.productEagerRepository.findAll(), true);
	}

	public ProductDTO getByProductIncludingRelationships(Long id) {
		Product product = this.productEagerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Product " + id + " does not exist"));
		return ProductConverter.convert(product, true);
	}

	public List<ProductDTO> getChildrenByProduct(Long id) {
		return ProductConverter.convert(this.productRespository.findByParentProduct_Id(id));
	}
	
	private void checkIfExistProduct(Product parent) {
		if (Objects.nonNull(parent)) {
			this.productRespository.findById(parent.getId())
					.orElseThrow(() -> new IllegalArgumentException("Product " + parent.getId() + " does not exist"));
		}
	}
}
