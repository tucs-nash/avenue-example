package com.avenue.com.avenue.product.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 320525537803073211L;

	private Long id;

	private String name;

	private String description;

	private List<ProductDTO> childProducts;

	private ProductDTO parentProduct;

	private List<ImageDTO> images;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductDTO> getChildProducts() {
		return childProducts;
	}

	public void setChildProducts(List<ProductDTO> childProducts) {
		this.childProducts = childProducts;
	}

	public ProductDTO getParentProduct() {
		return parentProduct;
	}

	public void setParentProduct(ProductDTO parentProduct) {
		this.parentProduct = parentProduct;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

}
