package jit.wxs.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author jitwxs
 * @date 2018/3/22 15:43
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String showIndex() {
        return "index";
    }

    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    @RequestMapping("/rest/page/item-edit")
    public String showItemEdit() {
        return "item-edit";
    }
}
