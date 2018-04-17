package jit.wxs.order.web;

import jit.wxs.cart.service.CartService;
import jit.wxs.common.utils.E3Result;
import jit.wxs.manager.pojo.TbItem;
import jit.wxs.manager.pojo.TbUser;
import jit.wxs.order.pojo.OrderInfo;
import jit.wxs.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 订单Controller
 * @author jitwxs
 * @date 2018/4/17 18:39
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/order-cart")
    public String showOrderCart(HttpServletRequest request, Model model) {
        // 从request中取tbUser，这里一定能取到，否则会被拦截器拦截
        TbUser tbUser = (TbUser) request.getAttribute("tbUser");

        // 根据用户id从Redis中取购物车信息
        List<TbItem> cartList = cartService.getCartList(tbUser.getId());
        model.addAttribute("cartList", cartList);

        return "order-cart";
    }

    @PostMapping("/create")
    public String createOrder(HttpServletRequest request, OrderInfo orderInfo, Model model) {
        System.out.println("Web:payment="+orderInfo.getPayment());
        TbUser tbUser = (TbUser) request.getAttribute("tbUser");
        //把用户信息添加到orderInfo中。
        orderInfo.setUserId(tbUser.getId());
        orderInfo.setBuyerNick(tbUser.getUsername());
        E3Result result = orderService.insertOrder(orderInfo);

        // 如果订单创建成功，清除购物车
        if(result.getStatus() == 200) {
            cartService.clearCart(tbUser.getId());
        }

        model.addAttribute("orderId", result.getData());
        model.addAttribute("payment", orderInfo.getPayment());
        return "success";
    }
}
