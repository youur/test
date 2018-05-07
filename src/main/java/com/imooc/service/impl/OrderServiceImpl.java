package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDto;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.OrderService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sing on 2018/4/26.
 * desc: youur4
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        BigDecimal orderAmount = new BigDecimal( BigInteger.ZERO );
        //1.查询商品数量，价格
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne( orderDetail.getProductId() );
            if (productInfo==null){
                throw new SellException( ResultEnum.PRODUCT_NOT_EXIST );
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply( new BigDecimal( orderDetail.getProductQuantity() ) )
                    .add( orderAmount );
            //订单详情入库
            orderDetail.setDetailId( KeyUtil.getUniqueKey() );
            orderDetail.setOrderId( orderId );
            //把productInfo的属性拷贝到orderdetail
            BeanUtils.copyProperties( productInfo,orderDetail );
            orderDetailRepository.save( orderDetail );

        }
        
        //写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties( orderDTO,orderMaster );
        orderMaster.setOrderId( orderId );
        orderMaster.setOrderAmount( orderAmount );
        orderMaster.setOrderStatus( OrderStatusEnum.NEW.getCode() );
        orderMaster.setPayStatus( PayStatusEnum.WAIT.getCode() );
        orderMasterRepository.save( orderMaster );
        //扣库存
        List<CartDto> cartDtoList = orderDTO.getOrderDetailList().stream().map( e->
                new CartDto( e.getProductId(),e.getProductQuantity() )
        ).collect( Collectors.toList());
        productService.decreaseStock( cartDtoList );
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals( OrderStatusEnum.NEW.getCode() )){
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus( OrderStatusEnum.FINISHED.getCode() );
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties( orderDTO,orderMaster );
        OrderMaster updateResult = orderMasterRepository.save( orderMaster );
        if (updateResult == null) {
            log.error("【完结订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals( OrderStatusEnum.NEW.getCode() )){
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (orderDTO.getPayStatus().equals( PayStatusEnum.WAIT.getCode() )) {
            log.error( "【订单支付完成】订单支付状态不正确,orderDTO={}",orderDTO );
            throw new SellException( ResultEnum.ORDER_UPDATE_FAIL );
        }
        //修改支付状态
        orderDTO.setPayStatus( PayStatusEnum.SUCCESS.getCode() );
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties( orderDTO,orderMaster );
        OrderMaster updateResult = orderMasterRepository.save( orderMaster );
        if (updateResult == null) {
            log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
