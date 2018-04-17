package jit.wxs.cart.interceptor;

import jit.wxs.common.utils.CookieUtils;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbUser;
import jit.wxs.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 * @author jitwxs
 * @date 2018/4/17 10:02
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    @Value("${cookie.cart.key}")
    private String CART_KEY;

    @Value("${cookie.token.key}")
    private String TOKEN_KEY;

    /**
     * handler执行之前调用
     * 功能：如果用户已经登陆，将登陆信息放入request
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取token
        String token = CookieUtils.getCookieValue(request, TOKEN_KEY, true);
        if(StringUtils.isBlank(token)) {
            return true;
        }

        // 查询用户信息
        E3Result result = tokenService.getUserInfo(token);
        if(result.getStatus() != 200) {
            return true;
        }

        // 将user对象放入token
        TbUser tbUser = (TbUser)result.getData();
        System.out.println(tbUser);
        request.setAttribute("tbUser", tbUser);
        System.out.println(request.getAttribute("tbUser"));
        return true;
    }

    /**
     * handler执行之后，modelAndView返回之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * handler执行之后调用，可以处理异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
