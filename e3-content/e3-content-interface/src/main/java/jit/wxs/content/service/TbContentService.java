package jit.wxs.content.service;

import com.baomidou.mybatisplus.service.IService;
import jit.wxs.manager.pojo.TbContent;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbContentService extends IService<TbContent> {
    List<TbContent> listByCategoryId(Long cid);

    void addContent(TbContent tbContent);

    void deleteById(Long id);
}
