/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.test;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Odds;
import cn.edu.sdut.softlab.repository.DataRepositoryImpl;
import cn.edu.sdut.softlab.util.CsvUtil;
import java.util.List;
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
        DataRepositoryImpl dataRepository = context.getBean(DataRepositoryImpl.class);
//        Long num = dataRepository.count();
//        logger.info("Data num: " + num);
//        List<Data> datalist = dataRepository.findAll();
//        for (Data data : datalist) {
//            for (Odds odd: data.getOdds()) {
//                logger.info("Data: " + data.toString() + "Odd"+ odd.toString());
//            }
//            
//        }
        List<Data> datalist = dataRepository.findByCompany("澳门");
        CsvUtil csv = new CsvUtil();
        csv.wirte("MatchMessage", datalist);

    }
}
