package jit.wxs.order.service;

import jit.wxs.common.utils.E3Result;
import jit.wxs.order.pojo.OrderInfo;

/**
 * @author jitwxs
 * @date 2018/4/17 21:51
 */
public interface OrderService {
    E3Result insertOrder(OrderInfo orderInfo);
}
