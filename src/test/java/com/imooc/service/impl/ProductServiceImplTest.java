package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.service.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void findOne() throws Exception {
        ProductInfo info = productService.findOne( "123456" );
        Assert.assertEquals( "123456", info.getProductId());
    }

    @Test
    public void findUpAll() throws Exception {
        List<ProductInfo> upAll = productService.findUpAll(  );
        Assert.assertEquals( 1,upAll.size() );
    }

    @Test
    public void findAll() throws Exception {
        PageRequest request = new PageRequest( 0,2 );
        Page<ProductInfo> all = productService.findAll( request );
        System.out.println(all);
    }

}