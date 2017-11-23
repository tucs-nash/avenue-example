package com.avenue.com.avenue.product.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avenue.com.avenue.product.entity.Image;
import com.avenue.com.avenue.product.entity.Product;
import com.avenue.com.avenue.product.model.ImageDTO;
import com.avenue.com.avenue.product.repository.ImageRepository;
import com.avenue.com.avenue.product.util.converter.ImageConverter;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	public List<ImageDTO> getByProductId(Long productId) {
		return ImageConverter.convert(this.imageRepository.findByProduct_Id(productId));
	}

	@Transactional
	public void createImage(Image image, Long productId) {
		image.setProduct(new Product(productId));
		this.imageRepository.save(image);
	}

	@Transactional
	public void updateImage(Long id, Image image, Long productId) {
		checkIfExistImage(id);
		image.setId(id);
		image.setProduct(new Product(productId));
		this.imageRepository.save(image);
	}

	@Transactional
	public void deleteImage(Long id) {
		this.imageRepository.deleteById(id);
	}

	private void checkIfExistImage(Long id) {
		this.imageRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Image " + id + " does not exist"));
	}
}
