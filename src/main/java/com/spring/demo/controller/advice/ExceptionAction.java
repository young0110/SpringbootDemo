package com.spring.demo.controller.advice;

import com.spring.demo.model.common.Msg;
import com.spring.demo.model.common.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 * 通知是切面的一种实现
 * @author zhangyang
 * @version 0.1
 * create 2019/1/7
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAction {

  /**
   * 通用异常处理，待完善
   * @param e 未捕获的异常
   * @return
   */
  @ExceptionHandler({Exception.class})
  public ResultData exception(Exception e) {
    log.error("未捕获的异常", e);
    return new ResultData<>(Msg.ERROR);
  }

}
