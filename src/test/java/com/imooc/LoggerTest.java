package com.imooc;

import com.fasterxml.jackson.databind.deser.Deserializers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Deserializers.Base.class)
public class LoggerTest {
    private  final Logger logger = LoggerFactory.getLogger( LoggerTest.class );
    @Test
    public  void  test1(){
        String name = "imooc";
        String password = "123456";
        logger.debug( "debug..." );
        logger.info( "info...." );
        logger.info( "name:{},password:{}",name,password );
        logger.error( "error..." );
    }
}
