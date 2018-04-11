package jit.wxs.manager.web.content;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import jit.wxs.common.pojo.EasyUITreeNode;
import jit.wxs.common.utils.E3Result;
import jit.wxs.content.service.TbContentCategoryService;
import jit.wxs.manager.pojo.TbContentCategory;
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
     * 返回内容目录树
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

    @PostMapping("/create")
    public E3Result createContentCat(Long parentId, String name) {
        return tbContentCategoryService.insert(parentId,name);
    }

    @PostMapping("/delete")
    public E3Result deleteContentCat(Long id) {
        TbContentCategory category = tbContentCategoryService.selectById(id);

        // 如果该节点有子节点，不允许删除
        if(category.getIsParent() == 1) {
            return E3Result.error("该节点拥有子节点");
        }

        // 找到其父节点
        TbContentCategory parent = tbContentCategoryService.selectById(category.getParentId());
        // 删除该节点
        tbContentCategoryService.deleteById(category);
        // 如果父节点没有其他子节点，将父节点变为子节点
        int count = tbContentCategoryService.selectCount(new EntityWrapper<TbContentCategory>()
                .eq("parent_id", parent.getId()));
        if(count == 0) {
            parent.setIsParent(0);
            tbContentCategoryService.updateById(parent);
        }
        return E3Result.ok();
    }

    @PostMapping("/update")
    public E3Result updateContentCat(Long id, String name) {
        TbContentCategory category = tbContentCategoryService.selectById(id);
        category.setName(name);
        tbContentCategoryService.updateById(category);

        return E3Result.ok();
    }
}
