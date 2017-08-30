/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.test;

import cn.edu.sdut.softlab.Repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author huanlu
 */
public class DataApp {
    
    static final Logger logger = LoggerFactory.getLogger(DataApp.class);
    
    public static void main(String[] args) {
        logger.info("DataApp start");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring-context.xml");
        DataRepository dataRepository = context.getBean(DataRepository.class);
        Long num = dataRepository.countData();
        logger.info("Data num: " + num);
    }
}
