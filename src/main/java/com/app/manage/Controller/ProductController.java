package com.app.manage.Controller;

import com.app.manage.dto.ProductDto;
import com.app.manage.service.product.ProductService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public List<ProductDto> getProductList() {
        List<ProductDto> list = productService.getProductList();
        return list;
    }

    @GetMapping("/detail/{productId}")
    public ProductDto getProductDetail(@PathVariable(name = "productId")String productId) {
        ProductDto detail = productService.getProductDetail(productId);
        return detail;
    }

    @PatchMapping("/modify/{productId}")
    public ProductDto modifyProduct(@PathVariable(name = "productId") String productId, ProductDto modify) {
        ProductDto modified = productService.modifyProduct(productId, modify);

        return modified;
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable(name = "productId")String productId) {
        String deleted = productService.deleteProduct(productId);

        return deleted;
    }

}
