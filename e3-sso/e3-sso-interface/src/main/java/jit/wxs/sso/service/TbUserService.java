package jit.wxs.sso.service;

import com.baomidou.mybatisplus.service.IService;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
public interface TbUserService extends IService<TbUser> {
    boolean checkRegister(String param, Integer type);

    boolean save(TbUser user);

    E3Result login(String username, String password);
}