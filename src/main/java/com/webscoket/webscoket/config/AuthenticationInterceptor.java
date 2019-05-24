//package com.webscoket.webscoket.config;
//
//import com.webscoket.webscoket.utils.JwtTokenUtil;
//import io.jsonwebtoken.Claims;
//import lombok.extern.java.Log;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
///**
// * Created by liujupeng on 2018/11/21.
// */
//@Log
//public class AuthenticationInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (handler instanceof org.springframework.web.servlet.resource.ResourceHttpRequestHandler) {
//            return true;
//        }
////        if (request.getServletPath().equals(JwtConstants.AUTH_PATH)) {
////            return true;
////        }
//        final String requestHeader = request.getHeader(JwtConstants.AUTH_HEADER);
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        LoginRequired methdAnno = method.getAnnotation(LoginRequired.class);
//        if (methdAnno == null) {
//            return true;
//        }
//        //从header中得到token
//        String authorization = request.getHeader(JwtConstants.AUTH_HEADER);
//        //验证token
//        Boolean tokenExpired = JwtTokenUtil.isTokenExpired(authorization);
//        if (tokenExpired) {
//            String uname = JwtTokenUtil.getUsernameFromToken(authorization);
//            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
//            request.setAttribute("authToken",uname);
//            return true;
//        }
//        //如果验证token失败，并且方法注明了Authorization，返回401错误
//        if (method.getAnnotation(LoginRequired.class) != null) {
//            response.sendError(401, "token失效，请重新登录");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//}
