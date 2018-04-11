package jit.wxs.manager.service.impl;

import jit.wxs.manager.pojo.TbUser;
import jit.wxs.manager.mapper.TbUserMapper;
import jit.wxs.manager.service.TbUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author jitwxs
 * @since 2018-03-21
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
