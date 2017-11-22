package com.avenue.com.avenue.product.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.avenue.com.avenue.product.resource.ProductResource;
import com.avenue.com.avenue.product.service.ImageService;
import com.avenue.com.avenue.product.service.ProductService;

@Configuration
public class JerseyConfiguration {

	@Bean
	public ProductResource productResource(ProductService productService, ImageService imageService) {
		return new ProductResource(productService, imageService);
	}
	
	@Bean
	public ResourceConfig config(ProductResource productResource) {
		ResourceConfig resourceConfig = new ResourceConfig();
		resourceConfig.register(productResource);
		return resourceConfig;
	}
}
