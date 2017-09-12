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
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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

    /**
     * 模糊查询记录中符合条件的(只返回公司名称字段)并去掉重复公司集合
     *
     * @param Company_name
     * @return
     */
    @RequestMapping(value = "/getlikecompany", method = RequestMethod.POST)
    @ResponseBody
    public Object getLikeCompany(@RequestParam("company_name") String Company_name) {
        List<Data> datas = dataRepository.findLikeCompany(Company_name);
        List<String> company_names = new ArrayList<>();
        for (Data data : datas) {
            if (!(data.getCompany().equals(""))) {
                company_names.add(data.getCompany());
            }
        }
        HashSet h = new HashSet(company_names);
        company_names.clear();
        company_names.addAll(h);
        return company_names;
    }

    @RequestMapping(value = "/getdata", method = RequestMethod.POST)
    @ResponseBody
    public Object getDatas(@RequestParam("company") String Company,
            @RequestParam(name = "league", required = false) String league,
            @RequestParam(name = "Year", required = false) String Year,
            @RequestParam(name = "Match", required = false) String Match) {
        return getDataList(Company, league, Year, Match);
    }

    public List<String> getJsonChange(List<Data> datalist) {
        List<String> JsonList = new ArrayList<>();
        datalist.forEach((data) -> {
            JsonList.add(JSON.toJSONString(data));
        });
        return JsonList;
    }

    public List<Data> getDataList(String Company, String League, String Year, String Match) {
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

    @RequestMapping(value = "/getpagedata", method = RequestMethod.POST)
    public Object getDataByPage(@RequestParam(value = "pageNum") int pageNum,
            @RequestParam(value = "pageSize") int pageSize) {
        return dataRepository.findDataByPage(null, pageNum, pageSize);
    }

    @RequestMapping(value = "/download")
    @ResponseBody
    public void download(
            @RequestParam("company") String Company,
            @RequestParam("league") String League,
            @RequestParam("year") String Year,
            @RequestParam("match") String Match,
            HttpServletResponse response) throws Exception {
        String s = "attachment; filename=" + combineFileName(Company, League, Year, Match);
        String fileName = new String(s.getBytes("UTF-8"),"iso-8859-1");//解决文件名中文乱码问题,勿改
        
        List<Data> datas = this.getDataList(Company, League, Year, Match);
        response.setHeader("Content-Disposition", fileName);
        response.setContentType("application/csv");
        CsvUtil csvUtil = new CsvUtil();
        csvUtil.process(datas, response.getOutputStream());
    }

    private String combineFileName(String company, String league, String year, String match) {
        StringBuilder sb = new StringBuilder();
        if (!(company == null || company.equals(""))) { // company not null ,append(company)
            sb.append(company.trim());
            if (!(league == null || league.equals(""))) { // league not null ,append(league)
                sb.append(league.trim());
                if (!(year == null || year.equals(""))) { // year not null ,append(year)
                    sb.append(year.trim());
                    if (!(match == null || match.equals(""))) { // match not null ,append(match)
                        sb.append(match.trim());
                    }
                }
            }
            sb.append(".csv");

        } else {// all null
            sb.append("所有记录.csv");
        }

        return sb.toString();
    }
}
