package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by sing on 2018/4/26.
 * desc: youur4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    private final String OPENID = "110110";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId( "1234567" );
        orderMaster.setBuyerName( "大师兄" );
        orderMaster.setBuyerPhone( "123546987" );
        orderMaster.setBuyerName( "长者" );
        orderMaster.setOrderAmount(new BigDecimal( 2.5 ) );
        orderMaster.setBuyerAddress( "上海交大" );
        orderMaster.setBuyerOpenid( "22" );
        OrderMaster result = repository.save( orderMaster );
        Assert.assertNotNull( result );
    }
    @Test
    public void findByBuyerOpenidTest(){
        PageRequest request = new PageRequest( 0, 1 );
        Page<OrderMaster> result = repository.findByBuyerOpenid( OPENID, request );
        System.out.println(result.getTotalElements());
    }
}