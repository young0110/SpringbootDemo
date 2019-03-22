package com.spring.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * description
 *
 * @author zhangyang
 * @version 0.1
 * create 2019/3/20
 */
@Slf4j
public class XmlReadUtils {

  public static void main(String[] args) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = factory.newDocumentBuilder();
    //InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("logback.xml");
    Document document = documentBuilder.parse(new ClassPathResource("logback.xml").getInputStream());
    NodeList nodeList = document.getChildNodes();
    NodeList tempNodeList = document.getChildNodes();

    while (tempNodeList.getLength() > 0) {
      for (int i = 0; i < nodeList.getLength(); i++) {
        Node node = nodeList.item(i);
        System.out.println(node.getAttributes().item(0).getNodeName());
        System.out.println(node.getAttributes().item(0).getNodeValue());
      }
    }

    System.out.println();

    //Map propertiesMap = properties2Map("a.properties");
    //Map yamlMap = yml2Map("application.yml");
    //Properties yamlProperties = yml2Properties("application.yml");
  }

  public static Properties yml2Properties(String name) {
    YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
    factory.setResources(new ClassPathResource(name));
    return factory.getObject();
  }

  public static Map yml2Map(String name) {
    YamlMapFactoryBean factory = new YamlMapFactoryBean();
    factory.setResources(new ClassPathResource(name));
    return factory.getObject();
  }

  public static Map properties2Map(String name) throws IOException {
    Map result = new LinkedHashMap();
    Properties properties = new Properties();
    properties.load(new ClassPathResource(name).getInputStream());
    properties.keySet().forEach(it ->
        result.put(String.valueOf(it), properties.getProperty(it.toString()))
    );
    return result;
  }



}
