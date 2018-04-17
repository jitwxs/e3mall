package jit.wxs.manager.service;

import com.baomidou.mybatisplus.service.IService;
import jit.wxs.manager.pojo.TbItemCat;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbItemCatService extends IService<TbItemCat> {
    List<TbItemCat> selectByParentId(Long parentId);
}
