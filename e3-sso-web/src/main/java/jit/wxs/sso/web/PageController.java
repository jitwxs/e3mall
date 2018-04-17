package jit.wxs.sso.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jitwxs
 * @date 2018/4/16 18:20
 */
@Controller
@RequestMapping("/page")
public class PageController {
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @GetMapping("/login")
    public String showLogin(String redirect, Model model) {
        // 重定向URL
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
