package com.avenue.com.avenue.product.util.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.avenue.com.avenue.product.entity.Image;
import com.avenue.com.avenue.product.model.ImageDTO;

public final class ImageConverter {

	public static List<ImageDTO> convert(Iterable<Image> entities) {
		return convert(entities, true);
	}

	public static List<ImageDTO> convert(Iterable<Image> entities, boolean product) {
		if (Objects.isNull(entities)) {
			return null;
		}

		List<ImageDTO> dtos = new ArrayList<>();
		entities.forEach(e -> dtos.add(convert(e, product)));
		return dtos;
	}

	public static ImageDTO convert(Image image, boolean product) {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setId(image.getId());
		imageDTO.setType(image.getType());

		if (product) {
			imageDTO.setProduct(ProductConverter.convert(image.getProduct()));
		}
		return imageDTO;
	}
}
