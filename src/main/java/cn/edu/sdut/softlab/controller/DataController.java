/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.repository.DataRepository;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    DataRepository dataRepository;
    
    @RequestMapping(value = "/getdatalist", method = RequestMethod.POST)
    @ResponseBody
    public Object getDataList() {
        return dataRepository.findAll();
    }

    @RequestMapping(value = "/getdata", method = RequestMethod.GET)
    public String data(ModelMap modelMap) {
        return "datalist";
    }
    
    public List<String> getJsonChange(List<Data> datalist){
        List<String> JsonList = new ArrayList<>();
        for (Data data : datalist) {
            JsonList.add(JSON.toJSONString(data));
        }
        return JsonList;
    }
    
    @RequestMapping(value = "/findPost",method = RequestMethod.POST)
    public List<Data> getSingleData(@RequestParam("Company") String Company,
                                    @RequestParam("League") String League,
                                    @RequestParam("Year") String Year,
                                    @RequestParam("Match") String Match){
        List<Data> dataList = new ArrayList<>();
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
    
}
