package com.app.manage.Controller;

import com.app.manage.dto.ProductDto;
import com.app.manage.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
@Log4j2
public class ProductController {

    private final ProductService productService;

    @PostMapping("/push")
    public String pushProduct(ProductDto dto) {
        String product = productService.pushProduct(dto);
        log.trace(product);
        return product;
    }
}
