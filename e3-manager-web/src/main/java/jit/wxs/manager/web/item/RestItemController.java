package jit.wxs.manager.web.item;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbItem;
import jit.wxs.manager.pojo.TbItemDesc;
import jit.wxs.manager.pojo.TbItemParamItem;
import jit.wxs.manager.service.TbItemDescService;
import jit.wxs.manager.service.TbItemParamItemService;
import jit.wxs.manager.service.TbItemParamService;
import jit.wxs.manager.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jitwxs
 * @date 2018/4/2 20:12
 */
@RestController
@RequestMapping("/rest/item")
public class RestItemController {
    @Autowired
    private TbItemService tbItemService;

    @Autowired
    private TbItemDescService tbItemDescService;

    @Autowired
    private TbItemParamItemService tbItemParamItemService;

    /**
     * 获取商品描述
     */
    @GetMapping("/query/item/desc/{itemId}")
    public E3Result getItemDesc(@PathVariable long itemId) {
        TbItemDesc itemDesc = tbItemDescService.geyById(itemId);
        if (itemDesc == null) {
            return E3Result.error("获取商品描述失败");
        } else {
            return E3Result.ok(itemDesc);
        }
    }

    /**
     * 获取商品规格
     */
    @GetMapping("/param/item/query/{itemId}")
    public E3Result getItemParam(@PathVariable long itemId) {
        TbItemParamItem itemParamItem = tbItemParamItemService.getByItemId(itemId);
        if(itemParamItem != null) {
            return E3Result.ok(itemParamItem);
        } else {
            return E3Result.error(null);
        }
    }

    @PostMapping("/update")
    public E3Result updateItem(TbItem item, String desc) {
        System.out.println(item);
        tbItemService.updateById(item);

        // 更新商品描述
        TbItemDesc itemDesc = tbItemDescService.geyById(item.getId());
        itemDesc.setItemDesc(desc);
        tbItemDescService.updateByItemId(itemDesc);

        return E3Result.ok();
    }

    /**
     * 删除商品
     */
    @PostMapping("/delete")
    public E3Result deleteItems(long[] ids) {
        for (long id : ids) {
            TbItem item = tbItemService.selectById(id);
            // 商品状态，1-正常，2-下架，3-删除
            item.setStatus(3);
            tbItemService.updateById(item);
        }
        return E3Result.ok();
    }

    /**
     * 下架商品
     */
    @PostMapping("/instock")
    public E3Result instockItems(long[] ids) {
        for (long id : ids) {
            TbItem item = tbItemService.selectById(id);
            // 商品状态，1-正常，2-下架，3-删除
            item.setStatus(2);
            tbItemService.updateById(item);
        }
        return E3Result.ok();
    }

    /**
     * 上架商品
     */
    @PostMapping("/reshelf")
    public E3Result reshelfItems(long[] ids) {
        for (long id : ids) {
            TbItem item = tbItemService.selectById(id);
            // 商品状态，1-正常，2-下架，3-删除
            item.setStatus(1);
            tbItemService.updateById(item);
        }
        return E3Result.ok();
    }

}
