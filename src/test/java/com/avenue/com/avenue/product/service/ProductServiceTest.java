package com.avenue.com.avenue.product.service;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.avenue.com.avenue.product.Application;
import com.avenue.com.avenue.product.entity.Product;
import com.avenue.com.avenue.product.model.ProductDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@Transactional
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	public void testFindAllProducts() {
		List<ProductDTO> list = productService.findAllProducts();
		Assert.assertNotNull(list);
		Assert.assertEquals(4, list.size());

		ProductDTO product1 = list.stream().filter(p -> p.getId().equals(1l)).findFirst().get();
		Assert.assertNull(product1.getChildProducts());
		Assert.assertNull(product1.getImages());
	}

	@Test
	public void testFindAllProductsIncludingRelationships() {
		List<ProductDTO> list = productService.findAllProductsIncludingRelationships();
		Assert.assertNotNull(list);
		Assert.assertEquals(4, list.size());

		ProductDTO product1 = list.stream().filter(p -> p.getId().equals(1l)).findFirst().get();
		Assert.assertNotNull(product1.getChildProducts());
		Assert.assertNotNull(product1.getImages());
	}

	@Test
	public void testGetByProduct() {
		ProductDTO byProduct = productService.getByProduct(1L);
		Assert.assertNotNull(byProduct);
		Assert.assertEquals("product 1", byProduct.getName());
		Assert.assertNull(byProduct.getChildProducts());
		Assert.assertNull(byProduct.getImages());
	}
	
	@Test
	public void testGetByProductIncludingRelationships() {
		ProductDTO byProduct = productService.getByProductIncludingRelationships(1L);
		Assert.assertNotNull(byProduct);
		Assert.assertEquals("product 1", byProduct.getName());
		Assert.assertNotNull(byProduct.getChildProducts());
		Assert.assertNotNull(byProduct.getImages());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetByProductWithError() {
		productService.getByProduct(10L);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetByProductIncludingRelationshipsWithError() {
		productService.getByProductIncludingRelationships(10L);
	}
	
	@Test
	public void testGetChildrenByProduct() {
		List<ProductDTO> childrenByProducts = productService.getChildrenByProduct(1L);
		Assert.assertNotNull(childrenByProducts);
		Assert.assertEquals(1, childrenByProducts.size());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testCreateProduct() {
		Product product = new Product();
		product.setName("Test");
		product.setDescription("Test Desc");
		
		productService.createProduct(product);
		
		Assert.assertNotNull(product.getId());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testUpdateProduct() {
		ProductDTO productDto =  productService.getByProduct(1L);
		Product product = new Product(productDto.getId());
		product.setName("Test");
		product.setDescription("Test Desc");
		
		productService.updateProduct(productDto.getId(), product);
		
		Assert.assertEquals(Long.valueOf(1L), product.getId());
		Assert.assertEquals("Test", product.getName());
	}
	
	@Test
	@Transactional
	@Rollback
	public void testDeleteProduct() {
		productService.deleteProduct(4L);
		
		try {
			productService.getByProduct(4L);			
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Product 4 does not exist", e.getMessage());
		}
	}
	
}
