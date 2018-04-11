package jit.wxs.manager.web.content;

import jit.wxs.common.utils.E3Result;
import jit.wxs.content.service.TbContentService;
import jit.wxs.manager.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jitwxs
 * @date 2018/4/4 22:29
 */
@RestController
@RequestMapping("/rest/content")
public class RestContentController {

    @Autowired
    private TbContentService tbContentService;

    @RequestMapping("/edit")
    public E3Result editContent(TbContent content) {
        tbContentService.updateById(content);
        return E3Result.ok();
    }
}
