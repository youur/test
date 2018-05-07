package com.imooc.dataobject;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@DynamicUpdate//表示动态更新
@Data
public class ProductCategory {
    /*类目id*/
    @Id//标识主键
    @GeneratedValue//表示生成唯一标识的主键，主键的生成策略
    private  Integer categoryId;
    /*类目名字*/
    private  String categoryName;
    /*'类目编号'*/
    private  Integer categoryType;
    /*'创建时间'*/
    private Date createTime;
    private Date updateTime;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
