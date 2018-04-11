package jit.wxs.portal.web;

import jit.wxs.content.service.TbContentService;
import jit.wxs.manager.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author jitwxs
 * @date 2018/4/6 17:14
 */
@Controller
public class PageController {
    @Value("${ad1.id}")
    private Long ad1Id;

    @Autowired
    private TbContentService tbContentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        // 得到首页大轮播图的List
        List<TbContent> ad1List = tbContentService.listByCategoryId(ad1Id);

        model.addAttribute("ad1List", ad1List);
        return "index";
    }
}
