package jit.wxs.manager.web;

import jit.wxs.common.utils.E3Result;
import jit.wxs.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 索引Controller
 * @author jitwxs
 * @date 2018/4/11 22:27
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private SearchItemService searchItemService;

    /**
     * 导入商品索引
     */
    @PostMapping("/item/import")
    public E3Result itemImport() {
        return searchItemService.addToSolr();
    }

}
