package com.app.manage.service.product;

import com.app.manage.dto.ProductDto;
import com.app.manage.entity.Product;

import java.util.List;

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

    default ProductDto entityToDto(Product entity) {
        ProductDto dto = ProductDto.builder()
                .productId(entity.getProductId())
                .productName(entity.getProductName())
                .productPrice(entity.getProductPrice())
                .productStock(entity.getProductStock())
                .location(entity.getLocation())
                .build();
        return dto;
    }


    // 상품 등록
    String pushProduct(ProductDto product);

    // 상품 목록 조회
    List<ProductDto> getProductList();

    // 상품 상세 조회
    ProductDto getProductDetail(String productId);

    // 상품 수정
    ProductDto modifyProduct(String productId,ProductDto modify);

    // 상품 삭제
    String deleteProduct(String productId);


}
