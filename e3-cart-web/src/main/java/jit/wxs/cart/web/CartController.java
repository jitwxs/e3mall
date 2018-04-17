package jit.wxs.cart.web;

import jit.wxs.cart.service.CartService;
import jit.wxs.common.utils.CookieUtils;
import jit.wxs.common.utils.E3Result;
import jit.wxs.common.utils.JsonUtils;
import jit.wxs.manager.pojo.TbItem;
import jit.wxs.manager.pojo.TbUser;
import jit.wxs.manager.service.TbItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车Controller
 * @author jitwxs
 * @date 2018/4/16 23:39
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private TbItemService tbItemService;

    @Autowired
    private CartService cartService;

    @Value("${cookie.cart.key}")
    private String CART_KEY;

    @Value("${cookie.cart.expire}")
    private Integer CART_EXPIRE;

    @GetMapping("/add/{itemId}")
    public String addCat(@PathVariable Long itemId, @RequestParam(defaultValue = "1")Integer num,
                         HttpServletRequest request, HttpServletResponse response) {
        // 从request域中取tbUser，如果取到，表示登陆状态
        TbUser tbUser = (TbUser)request.getAttribute("tbUser");
        if(tbUser != null) {
            // 登陆状态下，直接将购物车信息写入Redis，不存Cookie
            cartService.addCart(tbUser.getId(), itemId, num);
            return "cartSuccess";
        }

        // 如果未取到，表示未登陆，将购物车信息存入Cookie
        List<TbItem> itemList = getItemFromCookie(request);

        // 如果购物车中存在商品，在原有数量上增加
        boolean flag = false;
        for(TbItem item : itemList) {
            if(item.getId() == itemId.longValue()) {
                item.setNum(item.getNum() + num);
                flag = true;
                break;
            }
        }

        // 如果购物车中不存在商品，新增至购物车
        if(!flag) {
            TbItem item = tbItemService.getById(itemId);
            // 修改num属性作用从库存改为购物车数量
            item.setNum(num);
            // 修改image属性为一张图片
            String tmp = item.getImage();
            if(StringUtils.isNotBlank(tmp)) {
                item.setImage(tmp.split(",")[0]);
            }
            itemList.add(item);
        }

        // 回写Cookie
        CookieUtils.setCookie(request,response,CART_KEY,JsonUtils.objectToJson(itemList),CART_EXPIRE,true);

        return "cartSuccess";
    }

    @GetMapping("/cart")
    public String showCartList(HttpServletRequest request, HttpServletResponse response, Model model) {
        // 从Cookie中获取购物车列表
        List<TbItem> cartList = getItemFromCookie(request);
        // 判断用户是否登陆，如果登陆，删除Cookie中的数据，并存储到Redis中
        TbUser tbUser = (TbUser)request.getAttribute("tbUser");
        if(tbUser != null) {
            // 1、将数据保存到Redis中
            cartService.mergeCart(tbUser.getId(), cartList);
            // 2、删除Cookie
            CookieUtils.deleteCookie(request, response, CART_KEY);
            // 3、从Redis中取购物车列表
            cartList = cartService.getCartList(tbUser.getId());
        }

        model.addAttribute("cartList", cartList);

        return "cart";
    }

    @PostMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public E3Result updateCart(@PathVariable Long itemId, @PathVariable Integer num,
                               HttpServletRequest request, HttpServletResponse response) {
        // 判断用户是否登陆，如果登陆，更新Redis中数据
        TbUser tbUser = (TbUser)request.getAttribute("tbUser");
        if(tbUser != null) {
            cartService.updateCartNum(tbUser.getId(), itemId, num);
            return E3Result.ok();
        }

        // 如果未登陆，从Cookie中更新
        List<TbItem> itemList = getItemFromCookie(request);
        for(TbItem item : itemList) {
            if(item.getId() == itemId.longValue()) {
                item.setNum(num);
                break;
            }
        }
        // 回写Cookie
        CookieUtils.setCookie(request,response,CART_KEY,JsonUtils.objectToJson(itemList),CART_EXPIRE,true);

        return E3Result.ok();
    }

    @GetMapping("/delete/{itemId}")
    public String deleteCart(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response) {
        // 判断用户是否登陆，如果登陆，从Redis中删除
        TbUser tbUser = (TbUser)request.getAttribute("tbUser");
        if(tbUser != null) {
            cartService.deleteCartItem(tbUser.getId(), itemId);
            return "redirect:/cart/cart.html";
        }

        // 如果未登陆，从Cookie中删除
        List<TbItem> itemList = getItemFromCookie(request);
        for(TbItem item : itemList) {
            if(item.getId() == itemId.longValue()) {
                itemList.remove(item);
                break;
            }
        }

        // 回写Cookie
        CookieUtils.setCookie(request,response,CART_KEY,JsonUtils.objectToJson(itemList),CART_EXPIRE,true);

        return "redirect:/cart/cart.html";
    }

    /**
     * 从Cookie中取商品列表
     */
    private List<TbItem> getItemFromCookie(HttpServletRequest request) {
        String json = CookieUtils.getCookieValue(request, CART_KEY, true);
        if(StringUtils.isBlank(json)) {
            return new ArrayList<>();
        } else {
            return JsonUtils.jsonToList(json,TbItem.class);
        }
    }
}
