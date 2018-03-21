package jit.wxs.web;


import jit.wxs.pojo.TbItem;
import jit.wxs.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@RestController
@RequestMapping("/items")
public class TbItemController {
    @Autowired
    private TbItemService itemService;
    @GetMapping("/{id}")
    public TbItem getItemById(@PathVariable Long id) {
        TbItem item = null;
        if(id != null) {
            item = itemService.selectById(id);
        }

        return item;
    }
}

