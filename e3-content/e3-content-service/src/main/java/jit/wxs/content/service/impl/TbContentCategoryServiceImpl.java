package jit.wxs.content.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.common.utils.E3Result;
import jit.wxs.content.service.TbContentCategoryService;
import jit.wxs.manager.mapper.TbContentCategoryMapper;
import jit.wxs.manager.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbContentCategoryServiceImpl extends ServiceImpl<TbContentCategoryMapper, TbContentCategory> implements TbContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public E3Result insert(Long parentId, String name) {
        TbContentCategory parent = contentCategoryMapper.selectById(parentId);
        if(parent == null) {
            return E3Result.build(404,"父标签不存在");
        }

        TbContentCategory category = new TbContentCategory();

        category.setName(name);
        category.setParentId(parentId);
        // 该类目是否为父类目，1为true，0为false
        category.setIsParent(0);
        // 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
        category.setSortOrder(1);
        // 状态。可选值:1(正常),2(删除)
        category.setStatus(1);
        category.setCreated(new Date());
        category.setUpdated(new Date());

        Integer i = contentCategoryMapper.insert(category);

        if(i != 1) {
            return E3Result.build(404,"插入失败");
        }

        // 父该类目是否为父类目，1为true，0为false
        if(parent.getIsParent() == 0) {
            parent.setIsParent(1);
            Integer j = contentCategoryMapper.updateById(parent);
            if(j != 1) {
                return E3Result.build(404,"更新失败");
            }
        }

        return E3Result.ok(category);
    }
}
