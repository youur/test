package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
public interface ProductService {
    ProductInfo findOne(String productId);
    /*查询所有上架商品*/
    List<ProductInfo> findUpAll();
    /*分页查询*/
    Page<ProductInfo> findAll(Pageable pageable);
    /*加库存*/
    void increaseStock(List<CartDto> cartDtoList);
    /*减库存*/
    void decreaseStock(List<CartDto> cartDTOList);
}
