package com.spring.demo.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collections;

@Getter
@Setter
@ToString
@ApiModel("ResultData")
@SuppressWarnings("unchecked")
public class ResultData<T> implements Serializable {

  @ApiModelProperty(value = "返回状态", example = "0")
  private Integer code;
  @ApiModelProperty(value = "返回数据")
  private T data;
  @ApiModelProperty(value = "返回信息", example = "ok")
  private String msg;

  public ResultData(Integer code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public ResultData(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
    this.data = (T)Collections.emptyMap();
  }

  public ResultData(Msg msg) {
    this.code = msg.getCode();
    this.msg = msg.getMsg();
    this.data = (T)Collections.emptyMap();
  }

  public ResultData(@NotNull T data) {
    this.code = Msg.SUCCESS.getCode();
    this.msg = Msg.SUCCESS.getMsg();
    this.data = data;
  }


}
