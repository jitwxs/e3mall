package jit.wxs.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author jitwxs
 * @date 2018/3/22 16:25
 */
public class EasyUIDataGrid implements Serializable {
    private long total;

    private List rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
