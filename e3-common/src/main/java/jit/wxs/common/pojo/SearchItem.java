package jit.wxs.common.pojo;

import java.io.Serializable;

/**
 * Solr商品索引
 *
 * @author jitwxs
 * @date 2018/4/11 21:38
 */
public class SearchItem implements Serializable {
    /**
     * 商品id
     */
    private String id;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 商品卖点
     */
    private String sellPoint;
    /**
     * 商品价格
     */
    private Long price;
    /**
     * 商品图片
     */
    private String image;
    /**
     * 商品类别名
     */
    private String categoryName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String[] getImages() {
        if(this.image == null) {
            return null;
        } else {
            return this.image.split(",");
        }
    }
}
