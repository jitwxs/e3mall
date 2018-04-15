package jit.wxs.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 * @author jitwxs
 * @date 2018/4/13 14:37
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        logger.error("发生异常", e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error/exception");

        return modelAndView;
    }
}
