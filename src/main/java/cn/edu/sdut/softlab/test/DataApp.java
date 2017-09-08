/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.test;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.repository.DataRepositoryImpl;
import cn.edu.sdut.softlab.util.DateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
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
        //            if (data.getCompany().equals("澳门")) {
        //                for (Odds odd : data.getOdds()) {
        //                    logger.info("Data: " + data.toString() + "odd" + odd.toString());
        //                }
        //            }
        //            logger.info("Data: " + data.toString());
        //        }
        //        List<Data> datalist = dataRepository.findByCompany("澳门");
        //        CsvUtil csv = new CsvUtil();
        //        csv.wirte("MatchMessage", datalist);

        List<Data> datas = dataRepository.findLikeCompany("英超");
        for (Data data : datas) {
            logger.info("Data: " + data.toString());
        }
        
        String str = "2017-08-14T01:55:00Z";
        int in = str.indexOf("-");
        String date = str.substring(in - 4, in + 6);
        String time = str.substring(in + 7, in + 15);

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS");

        try {
            Date date1 = sdf.parse(date + " " +time);
            System.out.println(date1.toString());

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
