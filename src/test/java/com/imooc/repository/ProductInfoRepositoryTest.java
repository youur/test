package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = repository.save( productInfo );
        Assert.assertNotNull( result );
    }
    @Test
    public void findByProductStatusTest() throws Exception {
        List<ProductInfo> productInfos = repository.findByProductStatus( 0 );
        Assert.assertNotEquals(0, productInfos.size() );
        for (ProductInfo p:productInfos
             ) {
            System.out.println(productInfos);
        }

    }
    @Test
    public void findOne() throws Exception {
        ProductInfo one = repository.findOne( "123456" );
        System.out.println(one);
    }
}