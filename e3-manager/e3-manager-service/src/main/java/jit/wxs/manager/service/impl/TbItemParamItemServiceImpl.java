package jit.wxs.manager.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.manager.mapper.TbItemParamItemMapper;
import jit.wxs.manager.pojo.TbItemParamItem;
import jit.wxs.manager.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品规格和商品的关系表 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbItemParamItemServiceImpl extends ServiceImpl<TbItemParamItemMapper, TbItemParamItem> implements TbItemParamItemService {
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItemParamItem getByItemId(Long itemId) {
        List<TbItemParamItem> paramItems = itemParamItemMapper.selectList(new EntityWrapper<TbItemParamItem>().eq("item_id", itemId));
        if(paramItems != null && paramItems.size() > 0) {
            return paramItems.get(0);
        } else {
            return null;
        }
    }
}
