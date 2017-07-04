package me.akuo.blog.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component // 不可少
public class TimeInterceptor implements HandlerInterceptor { // 必须实现HandlerInterceptor接口


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("start", start);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        return;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        long start = (Long) request.getAttribute("start");
        request.removeAttribute("start");
        long end = System.currentTimeMillis();
        request.setAttribute("handlingTime", end - start);
    }
}