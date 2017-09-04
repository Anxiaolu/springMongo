/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.repository.DataRepository;
import cn.edu.sdut.softlab.util.CsvUtil;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author huanlu
 */
@Controller
@RequestMapping(value = "data")
public class DataController {
    
    final static Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    DataRepository dataRepository;
    
    @RequestMapping(value = "/getalldata", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllData() {
        return dataRepository.findAll();
    }

    @RequestMapping(value = "/getdata", method = RequestMethod.GET)
    public String data(ModelMap modelMap) {
        return "datalist";
    }
    
    public List<String> getJsonChange(List<Data> datalist){
        List<String> JsonList = new ArrayList<>();
        datalist.forEach((data) -> {
            JsonList.add(JSON.toJSONString(data));
        });
        return JsonList;
    }
    
    public List<Data> getDataList(String Company, String League, String Year, String Match) {
        List<Data> dataList = new ArrayList<>();
        logger.info("Find By Company: " + Company + "League: " + League + "Year: " + Year + "Match: " + Match);
        if (!(Company == null || Company.equals(""))) {
            if (!(League == null || League.equals(""))) {
                if (!(Year == null || Year.equals(""))) {
                    if (!(Match == null || Match.equals(""))) {
                        Data data = dataRepository.findOneData(Company, League, Year, Match);
                        dataList.add(data);
                        return dataList;
                    }
                    return dataRepository.findByCompanyAndLeagueAndYear(Company, League, Year);
                }
                return dataRepository.findByCompanyAndLeague(Company, League);
            }
            return dataRepository.findByCompany(Company);
        }
        return dataRepository.findAll();
    }
    
    @RequestMapping(value = "/findPost",method = RequestMethod.POST)
    public void WriteData(@RequestParam("company") String Company,
                                    @RequestParam("league") String League,
                                    @RequestParam("year") String Year,
                                    @RequestParam("match") String Match){
        logger.info("Company: " + Company + " League: " + League + " Year: " + Year + " Match: " + Match);
        List<Data> datas = this.getDataList(Company,League,Year,Match);
        CsvUtil csv = new CsvUtil();
        if (Company.equals("") &&
            League.equals("") || League == null &&
            Year.equals("") || Year == null &&
            Match.equals("") || Match == null) {
            
            logger.info("Company: " + Company + " League: " + League + " Year: " + Year + " Match: " + Match);
            
            String MatchMessage = "所有记录";
            csv.wirte(MatchMessage, datas);
        }
        String MatchMessage = Company + League + Year + Match;
        csv.wirte(MatchMessage, datas);
    }
    
    
}
