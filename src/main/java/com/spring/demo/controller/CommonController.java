package com.spring.demo.controller;

import com.spring.demo.model.common.Msg;
import com.spring.demo.model.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 * @author zhangyang
 * @version 0.1
 * create 2019/3/1
 */
@Api(tags = "通用接口")
@RestController
@RequestMapping("api/common")
public class CommonController {

  @ApiOperation("success")
  @GetMapping("success")
  public ResultData success() {
    return new ResultData(Msg.SUCCESS);
  }

}
