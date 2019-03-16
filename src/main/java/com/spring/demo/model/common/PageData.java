package com.spring.demo.model.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * description
 * @author zhangyang
 * @version 0.1
 * create 2019/3/16
 */
@Getter
@Setter
@ApiModel(value = "分页数据")
public class PageData<T> implements Serializable {

  @ApiModelProperty(value = "总数", example = "99")
  private long total;
  @ApiModelProperty(value = "总页数", example = "9")
  private int totalPage;
  @ApiModelProperty(value = "分页数据")
  private List<T> data = Collections.emptyList();

  public PageData(Page<T> page) {
    if (null != page) {
      this.total = page.getTotalElements();
      this.totalPage = page.getTotalPages();
      this.data = page.getContent();
    }
  }

}
