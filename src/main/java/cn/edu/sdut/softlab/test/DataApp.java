/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.test;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Odds;
import cn.edu.sdut.softlab.repository.impl.DataRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author huanlu
 */
public class DataApp {

    private static final Logger logger = LoggerFactory.getLogger(DataApp.class);

    public static void main(String[] args) {
        logger.info("DataApp start");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring-context.xml");
        DataRepositoryImpl dataRepository = context.getBean(DataRepositoryImpl.class);
        Data data = dataRepository.findOneData("Mansion88 (明升)", "西甲", "2017", "西班牙人VS莱加内斯赔率");
        logger.info(data.toString());
        for (Odds odd : data.getOdds()) {
            logger.info(odd.toString());
        }
//        List<Data> datas = dataRepository.findByMatch("曼彻斯特城VS利物浦赔率");
//        for (Data data : datas) {
//            logger.info("Data: " + data.toString());
//        }
    }
}
