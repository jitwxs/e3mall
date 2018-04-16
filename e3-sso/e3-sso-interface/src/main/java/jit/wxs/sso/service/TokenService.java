package jit.wxs.sso.service;

import jit.wxs.common.utils.E3Result;

/**
 * @author jitwxs
 * @date 2018/4/16 20:03
 */
public interface TokenService {
    E3Result getUserInfo(String token);
}
