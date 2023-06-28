package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logInInterceptor implements HandlerInterceptor {


    /**
     * 请求进来之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        System.out.println("user检测:---------------------");
        System.out.println("URL");
        System.out.println(request.getRequestURL());
        System.out.println("addr");
        System.out.println(request.getRemoteAddr());
        System.out.println(request.getHeader("userId"));
        String id = request.getHeader("userId");

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");

        if (id == null || user == null || !id.equals(user.toString())){
            response.setStatus(401);
            return false;
        }

        return true;

    }

    /**
     * 请求离开之前
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


    /**
     * 请求结束之后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
