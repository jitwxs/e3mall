package jit.wxs.manager.service;

import com.baomidou.mybatisplus.service.IService;
import jit.wxs.manager.pojo.TbItemParam;

import java.util.List;

/**
 * <p>
 * 商品规则参数 服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbItemParamService extends IService<TbItemParam> {
    List<TbItemParam> selectByItemCatId(Long itemCatId);
}
