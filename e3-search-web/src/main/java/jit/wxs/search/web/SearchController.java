package jit.wxs.search.web;

import jit.wxs.common.pojo.SearchItemResult;
import jit.wxs.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jitwxs
 * @date 2018/4/12 0:36
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @Value("${search.rows}")
    private Integer SEARCH_ROWS;

    @RequestMapping("/search")
    public String search(String keyword, @RequestParam(defaultValue = "1") Integer page, Model model) {
        try {
            keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
            SearchItemResult result = searchService.search(keyword, page, SEARCH_ROWS);

            model.addAttribute("page",page);
            model.addAttribute("totalPages",result.getTotalPages());
            model.addAttribute("recordCount",result.getRecordCount());
            model.addAttribute("itemList",result.getItemList());
            model.addAttribute("query", keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "search";
    }
}
