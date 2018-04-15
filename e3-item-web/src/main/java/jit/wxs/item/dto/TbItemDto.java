package jit.wxs.item.dto;

import jit.wxs.manager.pojo.TbItem;
import org.apache.commons.lang3.StringUtils;

/**
 * @author jitwxs
 * @date 2018/4/15 23:01
 */
public class TbItemDto extends TbItem {
    private String[] images;

    public TbItemDto(TbItem tbItem) {
        this.setId(tbItem.getId());
        this.setTitle(tbItem.getTitle());
        this.setSellPoint(tbItem.getSellPoint());
        this.setPrice(tbItem.getPrice());
        this.setNum(tbItem.getNum());
        this.setBarcode(tbItem.getBarcode());
        this.setImage(tbItem.getImage());
        this.setCid(tbItem.getCid());
        this.setStatus(tbItem.getStatus());
        this.setCreated(tbItem.getCreated());
        this.setUpdated(tbItem.getUpdated());
    }

    public String[] getImages() {
        if(StringUtils.isBlank(this.getImage())) {
            return null;
        } else {
            return this.getImage().split(",");
        }
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
