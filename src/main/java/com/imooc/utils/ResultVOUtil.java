package com.imooc.utils;

import com.imooc.VO.ResultVO;

/**
 * Created by sing on 2018/4/23.
 * desc: youur4
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setMsg( "成功" );
        resultVO.setCode( 0 );
        resultVO.setData( object );
        return resultVO;
    }
    public static ResultVO success(){
        return success( null );
    }
    public static ResultVO error(String msg,Integer code){
        ResultVO resultVO = new ResultVO<>();
        resultVO.setMsg( msg );
        resultVO.setCode( code );
        return resultVO;
    }
}
