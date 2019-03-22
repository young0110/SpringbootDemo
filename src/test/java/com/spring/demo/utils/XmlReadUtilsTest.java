package com.spring.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * description
 * @author zhangyang
 * @version 0.1
 * create 2019/3/20
 */
@Slf4j
public class XmlReadUtilsTest {

  @Test
  public void readXmlWithDom() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    log.info("path: {}", path);
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("logback.xml");
    Document document = documentBuilder.parse(in);
  }

}