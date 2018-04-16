package jit.wxs.item.web;

import jit.wxs.item.dto.TbItemDto;
import jit.wxs.manager.pojo.TbItem;
import jit.wxs.manager.pojo.TbItemDesc;
import jit.wxs.manager.service.TbItemDescService;
import jit.wxs.manager.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jitwxs
 * @date 2018/4/15 22:54
 */
@Controller
public class ItemController {
    @Autowired
    private TbItemService tbItemService;
    @Autowired
    private TbItemDescService tbItemDescService;

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable Long itemId, Model model) {
        TbItem item = tbItemService.getById(itemId);
        TbItemDto itemDto = new TbItemDto(item);
        TbItemDesc itemDesc = tbItemDescService.geyById(itemId);

        model.addAttribute("item", itemDto);
        model.addAttribute("itemDesc", itemDesc);
        return "item";
    }
}
