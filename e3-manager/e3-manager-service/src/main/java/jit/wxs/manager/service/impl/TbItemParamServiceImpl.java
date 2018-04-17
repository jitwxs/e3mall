package jit.wxs.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.manager.mapper.TbItemParamMapper;
import jit.wxs.manager.pojo.TbItemParam;
import jit.wxs.manager.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品规则参数 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbItemParamServiceImpl extends ServiceImpl<TbItemParamMapper, TbItemParam> implements TbItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;

    @Override
    public List<TbItemParam> selectByItemCatId(Long itemCatId) {
        return itemParamMapper.selectList(new EntityWrapper<TbItemParam>() .eq("item_cat_id", itemCatId));
    }
}
