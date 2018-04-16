package jit.wxs.sso.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import jit.wxs.common.jedis.JedisClient;
import jit.wxs.common.utils.E3Result;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.manager.mapper.TbUserMapper;
import jit.wxs.manager.pojo.TbUser;
import jit.wxs.sso.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${redis.token.pre}")
    private String TOKEN_PRE;

    @Value(("${redis.token.expire}"))
    private Integer TOKEN_EXPIRE;

    @Override
    public boolean checkRegister(String param, Integer type) {
        EntityWrapper<TbUser> entityWrapper = new EntityWrapper<>();
        // type=1，验证用户名;type=2，验证手机;type=3，验证邮箱
        switch (type) {
            case 1:
                entityWrapper.eq("username",param);
                break;
            case 2:
                entityWrapper.eq("phone",param);
                break;
            case 3:
                entityWrapper.eq("email",param);
                break;
            default:
                return false;
        }
        List<TbUser> list = userMapper.selectList(entityWrapper);
        if (list != null && list.size() > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean save(TbUser user) {
        // 有效性验证
        if(StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())
                || StringUtils.isBlank(user.getPhone())) {
            return false;
        }
        // 密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        // 保存数据库
        user.setCreated(new Date());
        user.setUpdated(new Date());
        userMapper.insert(user);

        return true;
    }

    @Override
    public E3Result login(String username, String password) {
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return E3Result.error("参数错误");
        }
        List<TbUser> list = userMapper.selectList(new EntityWrapper<TbUser>().eq("username",username));
        if(list == null || list.size()  == 0) {
            return E3Result.error("用户名或密码错误");
        }
        TbUser user = list.get(0);
        if(!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return E3Result.error("用户名或密码错误");
        }

        // 验证成功后，生成token
        String token = UUID.randomUUID().toString();
        //将token存入Redis
        // 为了安全，不将密码存入redis
        user.setPassword(null);
        jedisClient.set(TOKEN_PRE + token, JsonUtils.objectToJson(user));
        jedisClient.expire(TOKEN_PRE + token, TOKEN_EXPIRE);

        return E3Result.ok(token);
    }
}