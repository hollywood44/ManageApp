package com.app.manage.entity;

import com.app.manage.entity.utility.TimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_tbl")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends TimeEntity {

    // 상품아이디
    @Id
    private String productId;

    //상품명
    private String productName;

    // 상품 가격
    private Long productPrice;

    // 상품 재고
    private Long productStock;

    // 상품위치
    private String location;
}
