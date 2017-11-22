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
import com.avenue.com.avenue.product.entity.Image;
import com.avenue.com.avenue.product.model.ImageDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
@Transactional
public class ImageServiceTest {

	@Autowired
	private ImageService imageService;

	@Test
	public void testGetByProductId() {
		List<ImageDTO> byProductId = imageService.getByProductId(1L);
		Assert.assertNotNull(byProductId);
		Assert.assertEquals(2, byProductId.size());
	}

	@Test
	@Transactional
	@Rollback
	public void testCreateImage() {
		Image image = new Image();
		image.setType("test");

		imageService.createImage(image, 1L);
		Assert.assertNotNull(image.getId());
	}

	@Test
	@Transactional
	@Rollback
	public void testUpdateImage() {
		Image image = new Image();
		image.setType("test 2");

		imageService.updateImage(1L, image, 1L);
		Assert.assertEquals(image.getType(), "test 2");
	}

	@Test
	@Transactional
	@Rollback
	public void testDeleteImage() {
		imageService.deleteImage(1L);
		List<ImageDTO> byProductId = imageService.getByProductId(1L);
		Assert.assertNotNull(byProductId);
		Assert.assertEquals(1, byProductId.size());
	}
}
