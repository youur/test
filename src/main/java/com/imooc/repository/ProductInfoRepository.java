package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    /*根据商品的状态查询商品信息*/
    public List<ProductInfo> findByProductStatus(Integer productStatus);

}
