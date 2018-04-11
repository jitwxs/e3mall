package jit.wxs.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author jitwxs
 * @date 2018/4/12 0:11
 */
public class SearchItemResult implements Serializable {
    private List<SearchItem> itemList;

    private Long recordCount;

    private Integer totalPages;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
