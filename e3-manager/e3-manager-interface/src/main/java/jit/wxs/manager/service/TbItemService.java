package jit.wxs.manager.service;

import jit.wxs.manager.pojo.TbItem;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbItemService extends IService<TbItem> {
    /**
     * 插入商品
     */
    boolean insert(TbItem item, String desc,  String itemParams);

    TbItem getById(Long id);
}
