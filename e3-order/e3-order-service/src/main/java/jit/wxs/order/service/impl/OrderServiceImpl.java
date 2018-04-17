package jit.wxs.order.service.impl;

import jit.wxs.common.jedis.JedisClient;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.mapper.TbOrderItemMapper;
import jit.wxs.manager.mapper.TbOrderMapper;
import jit.wxs.manager.mapper.TbOrderShippingMapper;
import jit.wxs.manager.pojo.TbOrderItem;
import jit.wxs.manager.pojo.TbOrderShipping;
import jit.wxs.order.pojo.OrderInfo;
import jit.wxs.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author jitwxs
 * @date 2018/4/17 22:10
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${redis.order.key}")
    private String ORDER_KEY;

    @Value("${redis.order.id}")
    private String ORDER_ID;

    @Value("${redis.order.item.key}")
    private String ORDER_ITEM_KEY;

    @Override
    public E3Result insertOrder(OrderInfo orderInfo) {
        // 1、使用Redis生成订单号
        if(!jedisClient.exists(ORDER_KEY)) {
            jedisClient.set(ORDER_KEY, ORDER_ID);
        }
        String orderId = jedisClient.incr(ORDER_KEY).toString();
        System.out.println("Service:payment=" + orderInfo.getPayment());
        // 2、补全TbOrder信息，并插入数据库
        orderInfo.setOrderId(orderId);
        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
        orderInfo.setStatus(1);
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderMapper.insert(orderInfo);

        // 3、向订单明细表插入数据。
        List<TbOrderItem> orderItems = orderInfo.getOrderItems();
        for (TbOrderItem tbOrderItem : orderItems) {
            //生成明细id
            String odId = jedisClient.incr(ORDER_ITEM_KEY).toString();
            //补全的属性
            tbOrderItem.setId(odId);
            tbOrderItem.setOrderId(orderId);
            //向明细表插入数据
            orderItemMapper.insert(tbOrderItem);
        }

        // 3、向订单物流表插入数据
        TbOrderShipping orderShipping = orderInfo.getOrderShipping();
        orderShipping.setOrderId(orderId);
        orderShipping.setCreated(new Date());
        orderShipping.setUpdated(new Date());
        orderShippingMapper.insert(orderShipping);

        // 返回E3Result，包含订单号
        return E3Result.ok(orderId);
    }
}