package com.spring.demo.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 * @author zhangyang
 * @version 0.1
 * create 2019/2/20
 */
@Slf4j
@Component
@Singleton
public class BasicDataStack implements CommandLineRunner {

  public static Map<String, List<Map<String, String>>> stack = new HashMap<>();

  @Override
  public void run(String... args) throws Exception {
    init();
  }

  private void init() {
    log.info("初始化数据栈...");
  }

}
