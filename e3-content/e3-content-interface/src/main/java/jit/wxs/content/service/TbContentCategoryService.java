package jit.wxs.content.service;

import com.baomidou.mybatisplus.service.IService;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbContentCategory;

/**
 * <p>
 * 内容分类 服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbContentCategoryService extends IService<TbContentCategory> {
    E3Result insert(Long parentId, String name);
}
