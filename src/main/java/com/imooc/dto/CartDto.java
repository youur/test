package com.imooc.dto;

import lombok.Data;

/**
 * 购物车
 * Created by sing on 2018/5/4.
 * desc: youur4
 */
@Data
public class CartDto  {
    /*商品id*/
    private String productId;
    /*数量*/
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
