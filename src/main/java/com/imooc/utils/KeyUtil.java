package com.imooc.utils;

import java.util.Random;

/**
 * Created by sing on 2018/5/4.
 * desc: youur4
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间+随机数
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf( number );
    }
}
