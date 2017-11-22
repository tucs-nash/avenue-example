package com.avenue.com.avenue.product.util.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.avenue.com.avenue.product.entity.Product;
import com.avenue.com.avenue.product.model.ProductDTO;

public final class ProductConverter {

	public static List<ProductDTO> convert(Iterable<Product> entities) {
		return convert(entities, false);
	}

	public static List<ProductDTO> convert(Iterable<Product> entities, boolean relationship) {
		if (Objects.isNull(entities)) {
			return null;
		}

		List<ProductDTO> dtos = new ArrayList<>();
		entities.forEach(e -> dtos.add(convert(e, relationship)));
		return dtos;
	}

	public static ProductDTO convert(Product product) {
		return convert(product, false);
	}

	public static ProductDTO convert(Product product, boolean relationship) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());

		if (Objects.nonNull(product.getParentProduct())) {
			productDTO.setParentProduct(convert(product.getParentProduct(), false));
		}

		if (relationship) {
			productDTO.setImages(ImageConverter.convert(product.getImages(), false));
			productDTO.setChildProducts(clearChildProductParent(product.getChildProducts()));
		}

		return productDTO;
	}

	private static List<ProductDTO> clearChildProductParent(Set<Product> childProducts) {
		childProducts.forEach(cp -> cp.setParentProduct(null));
		return ProductConverter.convert(childProducts, false);
	}

}
