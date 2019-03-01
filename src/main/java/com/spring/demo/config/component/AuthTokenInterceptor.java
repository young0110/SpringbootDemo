package com.spring.demo.config.component;

import com.google.gson.Gson;
import com.spring.demo.model.common.Msg;
import com.spring.demo.model.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * authorization token interceptor
 * @author zhangyang
 * @version 0.1
 * create 2019/2/28
 */
@Slf4j
@Component
public class AuthTokenInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    log.info("Validate authorization token, path {}", request.getRequestURI());
    String token = request.getHeader("Authorization");
    if (null == token || !"young0110!".equals(token)) {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=utf-8");
      try (PrintWriter writer = response.getWriter()) {
        String msg = new Gson().toJson(new ResultData<>(Msg.AUTH_FAIL));
        log.info(msg);
        writer.print(msg);
      } catch (IOException e) {
        log.error("response error", e);
      }
      return false;
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}
