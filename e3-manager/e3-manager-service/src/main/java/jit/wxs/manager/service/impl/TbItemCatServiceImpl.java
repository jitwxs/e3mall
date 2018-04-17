package jit.wxs.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.manager.mapper.TbItemCatMapper;
import jit.wxs.manager.pojo.TbItemCat;
import jit.wxs.manager.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbItemCatServiceImpl extends ServiceImpl<TbItemCatMapper, TbItemCat> implements TbItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TbItemCat> selectByParentId(Long parentId) {
        return itemCatMapper.selectList(new EntityWrapper<TbItemCat>().eq("parent_id", parentId));
    }
}
