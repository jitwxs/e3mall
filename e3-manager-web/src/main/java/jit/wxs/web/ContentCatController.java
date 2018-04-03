package jit.wxs.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import jit.wxs.common.pojo.EasyUITreeNode;
import jit.wxs.common.utils.E3Result;
import jit.wxs.content.service.TbContentCategoryService;
import jit.wxs.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 内容分类Controller
 * @author jitwxs
 * @date 2018/4/4 0:08
 */
@RestController
@RequestMapping("/content/category")
public class ContentCatController {
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    /**
     * 内容目录树
     */
    @GetMapping("/list")
    public List<EasyUITreeNode> getContentCat(@RequestParam(required = false, defaultValue = "0") Long id) {
        List<TbContentCategory> categories = tbContentCategoryService.selectList(new EntityWrapper<TbContentCategory>()
                .eq("parent_id", id));
        List<EasyUITreeNode> list = new ArrayList<>();
        for(TbContentCategory category : categories) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(category.getId());
            node.setText(category.getName());
            node.setState(category.getIsParent() == 1 ? "closed" : "open");

            list.add(node);
        }
        return list;
    }

    /**
     * 添加子节点
     */
    @PostMapping("/create")
    public E3Result createContentCat(Long parentId, String name) {
        return tbContentCategoryService.insert(parentId,name);
    }
}
