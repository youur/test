package com.imooc.service;

import com.imooc.dataobject.ProductCategory;

import java.util.List;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
public interface CategoryService {
    public ProductCategory findOne(Integer categoryId);

    public List<ProductCategory> findAll() ;

    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) ;

    public ProductCategory save(ProductCategory productCategory);
}
