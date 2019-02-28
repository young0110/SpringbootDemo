package com.spring.demo.config.component;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * web接口日志
 * @author zhangyang
 * @version 0.1
 * create 2019/2/20
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

  @AfterReturning(value = "webLog()", returning = "rvt")
  public void doAfter(JoinPoint point, Object rvt) {
    log.info(getUrl(point) + new Gson().toJson(rvt));
  }

  private String getUrl(JoinPoint point) {
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    String s = point.getSignature().getDeclaringType().getSimpleName()
                   + "."
                   + point.getSignature().getName()
                   + " ";
    if (attr != null) {
      s += attr.getRequest().getRequestURI() + " ";
    }
    return s;
  }

  @Before(value = "webLog()")
  public void doBefore(JoinPoint point) {
    int i = 0;
    StringBuilder sb = new StringBuilder(getUrl(point));
    String[] params = ((CodeSignature) point.getStaticPart().getSignature()).getParameterNames();
    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    for (Object arg : point.getArgs()) {
      if (attr == null || (arg != null && arg != attr.getRequest() && arg != attr.getResponse())) {
        sb.append("&")
            .append(params[i])
            .append("=")
            .append(new Gson().toJson(arg));
      }
      i += 1;
    }
    log.info(sb.toString());
  }

  /**
   * 定义一个切入点.
   * 解释下：
   * <p>
   * ~ 第一个 * 代表任意修饰符及任意返回值.
   * ~ 第二个 * 任意包名
   * ~ 第三个 * 代表任意方法.
   * ~ 第四个 * 定义在controller包或者子包
   * ~ 第五个 * 任意方法
   * ~ .. 匹配任意数量的参数.
   */
  @Pointcut(value = "execution(public * com.spring..*.controller..*.*(..))")
  public void webLog() {
  }

}
