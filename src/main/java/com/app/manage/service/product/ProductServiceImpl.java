package com.app.manage.service.product;

import com.app.manage.dto.ProductDto;
import com.app.manage.entity.Product;
import com.app.manage.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<ProductDto> getProductList() {
        List<Product> entityList = productRepository.findAll();
        List<ProductDto> productList = entityList.stream().map(e -> entityToDto(e)).collect(Collectors.toList());
        return productList;
    }

    @Override
    public ProductDto getProductDetail(String productId) {
        Product entity = productRepository.findById(productId).orElseThrow(()->new NoSuchElementException("product not found"));
        ProductDto detail = entityToDto(entity);
        return detail;
    }

    @Override
    public ProductDto modifyProduct(String productId,ProductDto modify) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NoSuchElementException("product not found"));
        product = dtoToEntity(modify);
        productRepository.save(product);

        return entityToDto(product);
    }

    @Override
    public String deleteProduct(String productId) {
        productRepository.deleteById(productId);

        return productId;
    }
}
