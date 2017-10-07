/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.test;

import cn.edu.sdut.softlab.controller.DataController;
import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Odds;
import cn.edu.sdut.softlab.repository.impl.DataRepositoryImpl;
import cn.edu.sdut.softlab.util.StringUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    private static final Logger logger = LoggerFactory.getLogger(DataApp.class);

    public static void main(String[] args) {
        logger.info("DataApp start");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/spring/spring-context.xml");
        DataRepositoryImpl dataRepository = context.getBean(DataRepositoryImpl.class);
        DataController dataController = context.getBean(DataController.class);
        
//        Data data = dataRepository.findOneData("易胜博", "挪超", "2017", "克里斯蒂安松VS莫尔德赔率");
//        logger.info(data.toString());
//        DateUtil dateUtil = new DateUtil();
//        for (Odds odd : data.getOdds()) {
//            logger.info(odd.toString());
//            //logger.info(String.valueOf(dateUtil.getDatePoor(new Date(),odd.getUpdatetime())));
//        }
        
//        List<Data> datalist = new ArrayList<>();
//        datalist.add(data);
//        for (Data datase : dataController.getDatasByDateDiff(datalist, 2, new Date())) {
//            for (Odds odd : data.getOdds()) {
//                logger.info(datase.toString() + odd.toString());
//            }
//        }

        String companys = "皇家,必发";

        StringUtil stringUtil = new StringUtil();
        List<String> CompanyList = Arrays.asList(stringUtil.stringAnalytical(companys, ','));
        List<Data> datas = dataRepository.findDatasBySomeCompany("曼彻斯特城VS利物浦赔率", "英超", "2017", stringUtil.stringAnalytical(companys, ','));
        for (Data data : datas) {
            for (Odds Odd:data.getOdds()) {
                logger.info("Data: " + data.toString() + Odd.toString());
            }
        }
        
        String string = new String("");
        
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //long time = 1504412820000;
        Date date = new Date(Long.parseLong("1504412820000"));
        System.out.print(smf.format(date));
    }
    
}
