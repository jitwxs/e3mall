package jit.wxs.manager.service;

import jit.wxs.manager.pojo.TbItemParamItem;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品规格和商品的关系表 服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbItemParamItemService extends IService<TbItemParamItem> {
    TbItemParamItem getByItemId(Long itemId);
}
