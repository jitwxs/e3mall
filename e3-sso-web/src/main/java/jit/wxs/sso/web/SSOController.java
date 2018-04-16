package jit.wxs.sso.web;

import jit.wxs.common.utils.CookieUtils;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbUser;
import jit.wxs.sso.service.TbUserService;
import jit.wxs.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jitwxs
 * @date 2018/4/16 15:49
 */
@RestController
@RequestMapping("/user")
public class SSOController {
    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TokenService tokenService;

    @Value("${cookie.token.key}")
    private String TOKEN_KEY;

    @RequestMapping("/check/{param}/{type}")
    public E3Result checkRegister(@PathVariable String param, @PathVariable Integer type) {
        boolean flag = tbUserService.checkRegister(param, type);
        if(flag) {
            return E3Result.ok(true);
        } else {
            return E3Result.error(null,false);
        }
    }

    @PostMapping("/register")
    public E3Result register(TbUser tbUser) {
        boolean flag = tbUserService.save(tbUser);
        if(flag) {
            return E3Result.ok();
        } else {
            return E3Result.error("注册失败");
        }
    }

    @PostMapping("/login")
    public E3Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        E3Result result = tbUserService.login(username, password);
        if(result.getStatus() != 200) {
            return result;
        }

        // 将token写入cookie
        String token = result.getData().toString();
        CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        return result;
    }

    /**
     * 根据Token获取用户信息
     * @param token token
     * @param callback jsonp回掉方法名
     */
    @GetMapping("/token/{token}")
    public Object getUserInfo(@PathVariable String token, String callback) {
        E3Result result = tokenService.getUserInfo(token);

        if(StringUtils.isNotBlank(callback)) {
            // 处理ajax跨域访问,jsonp
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }
}
