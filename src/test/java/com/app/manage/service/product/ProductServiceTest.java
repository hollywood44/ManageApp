package com.app.manage.service.product;

import com.app.manage.entity.Product;
import com.app.manage.repository.ProductRepository;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Test
    @Rollback(value = false)
    void makeTestProduct() {
        IntStream.rangeClosed(2,10).forEach(i -> {
                    Product product = Product.builder()
                            .productId("T00"+i)
                            .productName("Test_Product_00"+i)
                            .productPrice(10000L)
                            .productStock(100L)
                            .location("t-1-1")
                            .build();
                    productRepository.save(product);
                });
    }




}