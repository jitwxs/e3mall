package jit.wxs.manager.web.item;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import jit.wxs.common.pojo.EasyUITreeNode;
import jit.wxs.manager.pojo.TbItemCat;
import jit.wxs.manager.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类Controller
 * @author jitwxs
 * @date 2018/4/4 0:05
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private TbItemCatService tbItemCatService;

    /**
     * 商品类目树
     */
    @PostMapping("/list")
    public List<EasyUITreeNode> getItemCat(@RequestParam(required = false, defaultValue = "0") Long id) {
        List<TbItemCat> itemCats = tbItemCatService.selectByParentId(id);

        List<EasyUITreeNode> result = new ArrayList<>();

        for (TbItemCat itemCat : itemCats) {
            EasyUITreeNode treeNode = new EasyUITreeNode();
            treeNode.setId(itemCat.getId());
            treeNode.setText(itemCat.getName());
            treeNode.setState(itemCat.getIsParent() == 1 ? "closed" : "open");

            result.add(treeNode);
        }
        return result;
    }
}
