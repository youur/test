package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne( 2 );
        productCategory.setCategoryType( 3 );
        repository.save( productCategory );
        //System.out.println(productCategory);
    }
    /*添加一条*/
    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory("百年孤独",4);
       /* productCategory.setCategoryName( "女生最爱" );
        productCategory.setCategoryType( 3 );*/
        repository.save( productCategory );
        Assert.assertNotNull( null,productCategory );
    }
    /*查找所有*/
    @Test
    public void findAll(){
        List<ProductCategory> list = repository.findAll();
        for (ProductCategory pc:list
             ) {
            System.out.println(pc);
        }
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> integers = Arrays.asList( 2, 3, 4 );
        List<ProductCategory> result = repository.findByCategoryTypeIn( integers );
        Assert.assertNotEquals( 0,result.size() );


    }
}