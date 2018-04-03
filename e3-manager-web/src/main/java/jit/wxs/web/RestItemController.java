package jit.wxs.web;

import jit.wxs.common.utils.E3Result;
import jit.wxs.pojo.TbItem;
import jit.wxs.pojo.TbItemDesc;
import jit.wxs.service.TbItemDescService;
import jit.wxs.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 获取商品描述
     */
    @GetMapping("/query/item/desc/{id}")
    public E3Result getItemDesc(@PathVariable long id) {
        TbItemDesc itemDesc = tbItemDescService.selectById(id);
        if (itemDesc == null) {
            return E3Result.build(404, "获取商品描述失败");
        } else {
            return E3Result.ok(itemDesc.getItemDesc());
        }
    }

//    /**
//     * 获取商品规格
//     */
//    @GetMapping("/param/item/query/{id}")
//    public E3Result getItemParam(@PathVariable long id) {
//
//    }

    /**
     * 删除商品
     */
    @PostMapping("/delete")
    public E3Result deletItems(long[] ids) {
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
