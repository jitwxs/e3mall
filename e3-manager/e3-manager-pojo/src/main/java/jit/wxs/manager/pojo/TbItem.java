package jit.wxs.manager.pojo;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public class TbItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id，同时也是商品编号
     */
    private Long id;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品卖点
     */
    private String sellPoint;
    /**
     * 商品价格，单位为：分
     */
    private Long price;
    /**
     * 库存数量
     */
    private Integer num;
    /**
     * 商品条形码
     */
    private String barcode;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 所属类目，叶子类目
     */
    private Long cid;
    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    @TableField(update="now()")
    private Date updated;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "TbItem{" +
        ", id=" + id +
        ", title=" + title +
        ", sellPoint=" + sellPoint +
        ", price=" + price +
        ", num=" + num +
        ", barcode=" + barcode +
        ", image=" + image +
        ", cid=" + cid +
        ", status=" + status +
        ", created=" + created +
        ", updated=" + updated +
        "}";
    }
}
