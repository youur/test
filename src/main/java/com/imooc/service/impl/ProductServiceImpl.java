package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDto;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sing on 2018/4/22.
 * desc: youur4
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {

        return repository.findOne( productId );
    }

    @Override
    public List<ProductInfo> findUpAll() {

        return repository.findByProductStatus( ProductStatusEnum.UP.getCode() );
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return repository.findAll( pageable );
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDTOList) {
        for (CartDto cartDto : cartDTOList) {
            ProductInfo productInfo = repository.findOne( cartDto.getProductId() );

            if (productInfo==null){
                throw new SellException( ResultEnum.PRODUCT_NOT_EXIST );
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result<0){
                throw new SellException( ResultEnum.PRODUCT_STOCK_ERROR );
            }
            productInfo.setProductStock( result );
            repository.save( productInfo );
        }
    }
}
