package jit.wxs.manager.web.item;

import com.baomidou.mybatisplus.plugins.Page;
import jit.wxs.common.pojo.EasyUIDataGrid;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbItem;
import jit.wxs.manager.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品Controller
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private TbItemService tbItemService;

    /**
     * 获取单一商品信息
     */
    @GetMapping("/{id}")
    public TbItem getItemById(@PathVariable Long id) {
        TbItem item = null;
        if (id != null) {
            item = tbItemService.selectById(id);
        }

        return item;
    }

    /**
     * 获取商品列表
     */
    @GetMapping("/list")
    public EasyUIDataGrid getItemList(Integer page, Integer rows) {
        Page<TbItem> itemPage = tbItemService.selectPage(new Page<>(page, rows));
        List<TbItem> list = itemPage.getRecords();

        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setTotal(itemPage.getTotal());
        result.setRows(list);

        return result;
    }

    /**
     * 保存商品
     * @param desc 商品描述
     */
    @PostMapping("/save")
    public E3Result saveItem(TbItem item, String desc, String itemParams) {
        boolean flag = tbItemService.insert(item, desc, itemParams);
        if (flag) {
            return E3Result.ok();
        } else {
            return E3Result.error("插入失败");
        }
    }
}