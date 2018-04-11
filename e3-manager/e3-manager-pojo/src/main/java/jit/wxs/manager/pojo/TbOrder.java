package jit.wxs.manager.pojo;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public class TbOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private String orderId;
    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String payment;
    /**
     * 支付类型，1、在线支付，2、货到付款
     */
    private Integer paymentType;
    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String postFee;
    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     */
    private Integer status;
    /**
     * 订单创建时间
     */
    private Date createTime;
    /**
     * 订单更新时间
     */
    @TableField(update="now()")
    private Date updateTime;
    /**
     * 付款时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date consignTime;
    /**
     * 交易完成时间
     */
    private Date endTime;
    /**
     * 交易关闭时间
     */
    private Date closeTime;
    /**
     * 物流名称
     */
    private String shippingName;
    /**
     * 物流单号
     */
    private String shippingCode;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 买家留言
     */
    private String buyerMessage;
    /**
     * 买家昵称
     */
    private String buyerNick;
    /**
     * 买家是否已经评价
     */
    private Integer buyerRate;


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getPostFee() {
        return postFee;
    }

    public void setPostFee(String postFee) {
        this.postFee = postFee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick;
    }

    public Integer getBuyerRate() {
        return buyerRate;
    }

    public void setBuyerRate(Integer buyerRate) {
        this.buyerRate = buyerRate;
    }

    @Override
    public String toString() {
        return "TbOrder{" +
        ", orderId=" + orderId +
        ", payment=" + payment +
        ", paymentType=" + paymentType +
        ", postFee=" + postFee +
        ", status=" + status +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", paymentTime=" + paymentTime +
        ", consignTime=" + consignTime +
        ", endTime=" + endTime +
        ", closeTime=" + closeTime +
        ", shippingName=" + shippingName +
        ", shippingCode=" + shippingCode +
        ", userId=" + userId +
        ", buyerMessage=" + buyerMessage +
        ", buyerNick=" + buyerNick +
        ", buyerRate=" + buyerRate +
        "}";
    }
}
