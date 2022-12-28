package com.app.manage.service.product;

import com.app.manage.dto.ProductDto;
import com.app.manage.entity.Product;
import com.app.manage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public String pushProduct(ProductDto product) {
        Optional<Product> check = productRepository.findById(product.getProductId());
        if (!check.isPresent()) {
            Product pushPd = dtoToEntity(product);
            return productRepository.save(pushPd).getProductId();
        } else {
            throw new RuntimeException("ProductID가 이미 있습니다.");
        }
    }


}
