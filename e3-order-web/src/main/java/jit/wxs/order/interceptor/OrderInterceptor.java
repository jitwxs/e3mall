package jit.wxs.order.interceptor;

import jit.wxs.cart.service.CartService;
import jit.wxs.common.utils.CookieUtils;
import jit.wxs.common.utils.E3Result;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.manager.pojo.TbItem;
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
 * 订单拦截器
 * @author jitwxs
 * @date 2018/4/17 18:45
 */
public class OrderInterceptor implements HandlerInterceptor {
    @Value("${SSO_URL}")
    private String SSO_URL;

    @Value("${cookie.token.key}")
    private String TOKEN_KEY;

    @Value("${cookie.cart.key}")
    private String CART_KEY;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CartService cartService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentUrl = request.getRequestURL().toString();
        // 1、从Cookie中取Token，如果未取到，表示未登陆，跳转登录
        String token = CookieUtils.getCookieValue(request, TOKEN_KEY);
        if(StringUtils.isBlank(token)) {
            // 将当前URL传入SSO登陆系统，方便登陆成功后回跳
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + currentUrl);
            return false;
        }
        // 2、用Token从Redis中查询鉴权信息，如果未取到，表示登陆过期，跳转登陆
        E3Result result = tokenService.getUserInfo(token);
        if(result.getStatus() != 200) {
            // 将当前URL传入SSO登陆系统，方便登陆成功后回跳
            response.sendRedirect(SSO_URL + "/page/login?redirect=" + currentUrl);
            return false;
        }
        // 3、如果取到，合并Cookie购物车与Redis购物车
        TbUser tbUser = (TbUser) result.getData();
        String json = CookieUtils.getCookieValue(request, CART_KEY, true);
        if(StringUtils.isNotBlank(json)) {
            cartService.mergeCart(tbUser.getId(), JsonUtils.jsonToList(json,TbItem.class));
        }

        // 将用户信息存入Request
        request.setAttribute("tbUser", tbUser);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
