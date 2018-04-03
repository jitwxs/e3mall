package jit.wxs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.common.utils.IDUtils;
import jit.wxs.mapper.TbItemDescMapper;
import jit.wxs.mapper.TbItemMapper;
import jit.wxs.pojo.TbItem;
import jit.wxs.pojo.TbItemDesc;
import jit.wxs.service.TbItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbItemServiceImpl extends ServiceImpl<TbItemMapper, TbItem> implements TbItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public boolean insert(TbItem item, String desc) {
        Date date = new Date();

        long id = IDUtils.genItemId();
        // 商品状态，1-正常，2-下架，3-删除
        item.setId(id);
        item.setStatus(1);
        item.setCreated(date);
        item.setUpdated(date);
        int i = itemMapper.insert(item);

        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(id);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        int j = itemDescMapper.insert(itemDesc);

        return i == 1 && j == 1;
    }
}
