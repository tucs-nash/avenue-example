package com.avenue.com.avenue.product.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.util.Assert;

import com.avenue.com.avenue.product.entity.Product;
import com.avenue.com.avenue.product.model.ProductDTO;
import com.avenue.com.avenue.product.service.ImageService;
import com.avenue.com.avenue.product.service.ProductService;

@Path("products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	private ProductService productService;
	private ImageService imageService;

	public ProductResource(ProductService productService, ImageService imageService) {
		this.productService = productService;
		this.imageService = imageService;
	}

	@GET
	public List<ProductDTO> getProducts() {
		return this.productService.findAllProducts();
	}

	@Path("{id}")
	@GET
	public ProductDTO getByProduct(@PathParam("id") Long id) {
		return this.productService.getByProduct(id);
	}

	@Path("complete")
	@GET
	public List<ProductDTO> getProductsIncludingRelationships() {
		return this.productService.findAllProductsIncludingRelationships();
	}

	@Path("{id}/complete")
	@GET
	public ProductDTO getByProductIncludingRelationships(@PathParam("id") Long id) {
		return this.productService.getByProductIncludingRelationships(id);
	}

	@Path("{id}/children")
	@GET
	public List<ProductDTO> getChildrenByProduct(@PathParam("id") Long id) {
		return this.productService.getChildrenByProduct(id);		
	}

	@Path("{id}/images")
	public ImageResource getImagesByProduct(@PathParam("id") Long id) {
		return new ImageResource(imageService, id);
	}
	
	@POST
	public Response createProduct(Product product) {
		Assert.notNull(product, "Product cannot be null");
		this.productService.createProduct(product);
		return Response.ok().build();
	}

	@Path("{id}")
	@PUT
	public Response updateProduct(@PathParam("id") Long id, Product product) {
		Assert.notNull(product, "Product cannot be null");
		this.productService.updateProduct(id, product);
		return Response.ok().build();
	}

	@Path("{id}")
	@DELETE
	public Response deleteProduct(@PathParam("id") Long id) {
		this.productService.deleteProduct(id);
		return Response.ok().build();
	}
	
}
