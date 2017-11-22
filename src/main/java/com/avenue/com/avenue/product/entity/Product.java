package com.avenue.com.avenue.product.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

@Entity(name = "product")
@NamedEntityGraph(name = "Product.eager",
attributeNodes = {
    @NamedAttributeNode(value = "childProducts"),
    @NamedAttributeNode(value = "images")
})
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name = "parent_product_id")
	private Set<Product> childProducts;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_product_id")
	private Product parentProduct;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name = "product_id")
	private Set<Image> images;

	public Product() {
	}

	public Product(Long productId) {
		this.id = productId;
	}

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

	public Set<Product> getChildProducts() {
		return childProducts;
	}

	public void setChildProducts(Set<Product> childProducts) {
		this.childProducts = childProducts;
	}

	public Product getParentProduct() {
		return parentProduct;
	}

	public void setParentProduct(Product parentProduct) {
		this.parentProduct = parentProduct;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

}
