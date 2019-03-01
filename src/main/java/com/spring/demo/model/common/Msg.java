package com.spring.demo.model.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Msg {

  //常规的

  SUCCESS("成功", 0),

  FAIL("失败", -1),

  //1xxx 系统的

  ERROR("系统异常", 1001),

  PARAM("参数错误", 1002),

  EMPTY("无数据", 1003),

  //2xxx 业务的
  AUTH_FAIL("无效的token", 2001);

  private Integer code;
  private String  msg;

  Msg(String msg, Integer code) {
    this.msg = msg;
    this.code = code;
  }

}
