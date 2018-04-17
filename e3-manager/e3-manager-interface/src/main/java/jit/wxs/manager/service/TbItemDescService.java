package jit.wxs.manager.service;

import jit.wxs.manager.pojo.TbItemDesc;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 商品描述表 服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbItemDescService extends IService<TbItemDesc> {
    TbItemDesc geyById(Long id);

    boolean updateByItemId(TbItemDesc itemDesc);
}
