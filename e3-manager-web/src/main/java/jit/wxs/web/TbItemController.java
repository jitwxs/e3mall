package jit.wxs.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import jit.wxs.common.pojo.EasyUIDataGrid;
import jit.wxs.common.pojo.EasyUITreeNode;
import jit.wxs.pojo.TbItem;
import jit.wxs.pojo.TbItemCat;
import jit.wxs.service.TbItemCatService;
import jit.wxs.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/item")
public class TbItemController {
    @Autowired
    private TbItemService tbItemService;

    @Autowired
    private TbItemCatService tbItemCatService;

    @GetMapping("/{id}")
    public TbItem getItemById(@PathVariable Long id) {
        TbItem item = null;
        if(id != null) {
            item = tbItemService.selectById(id);
        }

        return item;
    }

    @GetMapping("/list")
    public EasyUIDataGrid getItemList(Integer page, Integer rows) {
        Page<TbItem> itemPage = tbItemService.selectPage(new Page<>(page, rows));
        List<TbItem> list = itemPage.getRecords();

        EasyUIDataGrid result = new EasyUIDataGrid();
        result.setTotal(itemPage.getTotal());
        result.setRows(list);

        return result;
    }

    @PostMapping("/cat/list")
    public List<EasyUITreeNode> getItemCat(@RequestParam(required = false, defaultValue = "0") Long id) {
        List<TbItemCat> itemCats = tbItemCatService.selectList(new EntityWrapper<TbItemCat>()
                .eq("parent_id", id));

        List<EasyUITreeNode> result = new ArrayList<>();

        for (TbItemCat itemCat : itemCats) {
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setId(itemCat.getId());
            treeNode.setText(itemCat.getName());
            treeNode.setState(itemCat.getIsParent() == 1 ? "closed" : "open");

            result.add(treeNode);
        }
        return result;
    }


}