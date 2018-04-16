package jit.wxs.sso.service.impl;

import jit.wxs.common.jedis.JedisClient;
import jit.wxs.common.utils.E3Result;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.manager.pojo.TbUser;
import jit.wxs.sso.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author jitwxs
 * @date 2018/4/16 20:03
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private JedisClient jedisClient;

    @Value("${redis.token.pre}")
    private String TOKEN_PRE;

    @Value(("${redis.token.expire}"))
    private Integer TOKEN_EXPIRE;

    @Override
    public E3Result getUserInfo(String token) {
        String json = jedisClient.get(TOKEN_PRE + token);
        if(json == null) {
            return E3Result.error("用户凭证已过期");
        }

        //更新redis过期时间
        jedisClient.expire(TOKEN_PRE + token,TOKEN_EXPIRE);
        TbUser user = JsonUtils.jsonToPojo(json,TbUser.class);
        return E3Result.ok(user);
    }
}
