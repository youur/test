package com.imooc.enums;

import lombok.Getter;

/**
 * Created by sing on 2018/4/26.
 * desc: youur4
 */
@Getter
public enum OrderStatusEnum {
    NEW( 0, "新订单" ),
    FINISHED( 1, "完结" ),
    CANCEL(2,"已取消"),
    ;
    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
