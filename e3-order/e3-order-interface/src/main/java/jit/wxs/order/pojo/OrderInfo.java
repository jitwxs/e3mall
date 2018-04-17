package jit.wxs.order.pojo;

import jit.wxs.manager.pojo.TbOrder;
import jit.wxs.manager.pojo.TbOrderItem;
import jit.wxs.manager.pojo.TbOrderShipping;

import java.util.List;

/**
 * 订单信息实体
 * @author jitwxs
 * @date 2018/4/17 21:52
 */
public class OrderInfo extends TbOrder {
    private List<TbOrderItem> orderItems;

    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
