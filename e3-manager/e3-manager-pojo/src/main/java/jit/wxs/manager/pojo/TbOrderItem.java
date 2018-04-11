package jit.wxs.manager.pojo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public class TbOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    /**
     * 商品id
     */
    private String itemId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 商品购买数量
     */
    private Integer num;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品单价
     */
    private Long price;
    /**
     * 商品总金额
     */
    private Long totalFee;
    /**
     * 商品图片地址
     */
    private String picPath;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public String toString() {
        return "TbOrderItem{" +
        ", id=" + id +
        ", itemId=" + itemId +
        ", orderId=" + orderId +
        ", num=" + num +
        ", title=" + title +
        ", price=" + price +
        ", totalFee=" + totalFee +
        ", picPath=" + picPath +
        "}";
    }
}
