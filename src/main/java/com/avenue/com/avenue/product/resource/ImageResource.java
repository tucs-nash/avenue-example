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

import com.avenue.com.avenue.product.entity.Image;
import com.avenue.com.avenue.product.model.ImageDTO;
import com.avenue.com.avenue.product.service.ImageService;

@Produces(MediaType.APPLICATION_JSON)
public class ImageResource {

	private ImageService imageService;
	private Long productId;

	public ImageResource(ImageService imageService, Long productId) {
		this.imageService = imageService;
		this.productId = productId;
	}
	
	@GET
	public List<ImageDTO> getImages() {
		return this.imageService.getByProductId(productId);
	}
	
	@POST
	public Response createImage(Image image) {
		Assert.notNull(image, "Image cannot be null");
		this.imageService.createImage(image, productId);
		return Response.ok().build();
	}
	
	@Path("{id}")
	@PUT
	public Response updateImage(@PathParam("id") Long id, Image image) {
		Assert.notNull(image, "Image cannot be null");
		image.setId(id);
		this.imageService.updateImage(id, image, productId);
		return Response.ok().build();
	}
	
	@Path("{id}")
	@DELETE
	public Response deleteImage(@PathParam("id") Long id) {
		this.imageService.deleteImage(id);
		return Response.ok().build();
	}
}
