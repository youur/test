package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * Created by sing on 2018/4/26.
 * desc: youur4
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
