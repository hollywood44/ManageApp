package com.app.manage.service.product;

import com.app.manage.dto.ProductDto;
import com.app.manage.entity.Product;

public interface ProductService {

    default Product dtoToEntity(ProductDto dto) {
        Product entity = Product.builder()
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .productPrice(dto.getProductPrice())
                .productStock(dto.getProductStock())
                .location(dto.getLocation())
                .build();
        return entity;
    }
    String pushProduct(ProductDto product);
}
