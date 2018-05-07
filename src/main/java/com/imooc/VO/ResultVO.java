package com.imooc.VO;

import lombok.Data;

/**
 * http返回最外层对象
 * Created by sing on 2018/4/23.
 * desc: youur4
 */
@Data
public class ResultVO<T> {
    /*提示信息*/
    private String msg;
    /*错误码*/
    private Integer code;
    /*返回的具体内容*/
    private T data;
}
