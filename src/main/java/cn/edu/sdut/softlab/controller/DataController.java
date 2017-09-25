/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Odds;
import cn.edu.sdut.softlab.repository.DataRepository;
import cn.edu.sdut.softlab.util.CsvUtil;
import cn.edu.sdut.softlab.util.DateUtil;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Date;
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
     * @param Match
     * @return
     */
    @RequestMapping(value = "/getlikematch", method = RequestMethod.POST)
    @ResponseBody
    public Object getLikeCompany(@RequestParam("match_name") String Match) {
        List<Data> datas = dataRepository.findLikeMatch(Match);
        List<String> dataList = new ArrayList<>();
        for (Data data : datas) {
            if (!(data.getCompany().equals(""))) {
                dataList.add(data.getMatch());
            }
        }
        HashSet h = new HashSet(dataList);
        dataList.clear();
        dataList.addAll(h);
        return dataList;
    }

    @RequestMapping(value = "/getleaguebymatch", method = RequestMethod.POST)
    @ResponseBody
    public Object getLeagueByMatch(@RequestParam("match") String Match) {
        List<Data> datas = dataRepository.findByMatch(Match);
        List<String> dataList = new ArrayList<>();
        for (Data data : datas) {
            if (!(data.getCompany().equals(""))) {
                dataList.add(data.getLeague());
            }
        }
        HashSet h = new HashSet(dataList);
        dataList.clear();
        dataList.addAll(h);
        return dataList;
    }

    @RequestMapping(value = "/getyearbyleague", method = RequestMethod.POST)
    @ResponseBody
    public Object getYearByLeague(@RequestParam("match") String Match,
            @RequestParam(name = "league", required = false) String League) {
        List<Data> datas = dataRepository.findByMatchAndLeague(Match, League);
        List<String> dataList = new ArrayList<>();
        for (Data data : datas) {
            if (!(data.getCompany().equals(""))) {
                dataList.add(data.getYear());
            }
        }
        HashSet h = new HashSet(dataList);
        dataList.clear();
        dataList.addAll(h);
        return dataList;
    }

    @RequestMapping(value = "/getcompanybyother", method = RequestMethod.POST)
    @ResponseBody
    public Object getCompanyByMatch(@RequestParam("match") String Match,
            @RequestParam(name = "league", required = false) String League,
            @RequestParam(name = "year", required = false) String Year) {
        List<Data> datas = dataRepository.findByMatchAndLeagueAndYear(Match, League, Year);
        List<String> dataList = new ArrayList<>();
        for (Data data : datas) {
            if (!(data.getCompany().equals(""))) {
                dataList.add(data.getCompany());
            }
        }
        HashSet h = new HashSet(dataList);
        dataList.clear();
        dataList.addAll(h);
        return dataList;
    }

    @RequestMapping(value = "/getdata", method = RequestMethod.POST)
    @ResponseBody
    public Object getDatas(@RequestParam(name = "company", required = false) String Company,
            @RequestParam(name = "league", required = false) String league,
            @RequestParam(name = "year", required = false) String Year,
            @RequestParam(name = "match") String Match) {
        return getDataList(Company, league, Year, Match);
    }

    public List<String> getJsonChange(List<Data> datalist) {
        List<String> JsonList = new ArrayList<>();
        datalist.forEach((data) -> {
            JsonList.add(JSON.toJSONString(data));
        });
        return JsonList;
    }
    
    /**
     * 遍历集合查找在给定时间之前差值最小的时间并返回对应的Odd数据
     * @param Odds
     * @param date 
     */
    public Odds getDateMinDiff(List<Odds> Odds, Date date) {
        long diff = new Long(999999999);
        DateUtil dateUtil = new DateUtil();
        Odds odd = new Odds();
        for (Odds Odd : Odds) {
            if (!(Odd.getUpdatetime() == null || Odd.getUpdatetime().equals(""))) {
                if (dateUtil.getDatePoor(date, Odd.getUpdatetime()) < diff) {
                    diff = dateUtil.getDatePoor(date, Odd.getUpdatetime());
                    odd = Odd;
                }
            }
        }
        return odd;
    }
    
    public List<Data> getDatasByDateDiff(List<Data> datas,int n,Date date){
        List<Data> datalist = new ArrayList<>();
        for (Data data : datas) {
            for (Odds  odd : data.getOdds()) {
                if (odd.equals(this.getDateMinDiff(data.getOdds(), date))) {
                    List<Odds> Oddses = data.getOdds().subList(data.getOdds().indexOf(odd), n);
                    datalist.add(new Data(data.getCompany(), data.getLeague(), data.getYear(), data.getMatch(), Oddses));
                }
            }   
        }
        return datalist;
    }

    public List<Data> getDataList(String Company, String League, String Year, String Match) {
        List<Data> dataList = new ArrayList<>();
        if (!(Match == null || Match.equals(""))) {
            if (!(League == null || League.equals(""))) {
                if (!(Year == null || Year.equals(""))) {
                    if (!(Company == null || Company.equals(""))) {
                        Data data = dataRepository.findOneData(Company, League, Year, Match);
                        dataList.add(data);
                        return dataList;
                    }
                    return dataRepository.findByMatchAndLeagueAndYear(Match, League, Year);
                }
                return dataRepository.findByMatchAndLeague(Match, League);
            }
            return dataRepository.findByMatch(Match);
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
            @RequestParam("time") String Time,
            @RequestParam("datanum")Integer Num,
            HttpServletResponse response) throws Exception {
        String s = "attachment; filename=" + combineFileName(Company, League, Year, Match);
        String fileName = new String(s.getBytes("UTF-8"), "iso-8859-1");//解决文件名中文乱码问题,勿改

        List<Data> datas = this.getDataList(Company, League, Year, Match);
        response.setHeader("Content-Disposition", fileName);
        response.setContentType("application/csv");
        CsvUtil csvUtil = new CsvUtil();
        DateUtil dateUtil = new DateUtil();
        Time = Time.replace('T', ' ');
        Time = Time + ":00";
//        if ((Time.equals("")||Time == null) && (Num.equals("")||Num == null)) {
//            csvUtil.process(datas, response.getOutputStream());
//        }
        csvUtil.process(this.getDatasByDateDiff(datas, Num, dateUtil.strToDateLong(Time)), response.getOutputStream());
    }

    private String combineFileName(String company, String league, String year, String match) {
        StringBuilder sb = new StringBuilder();
        if (!(match == null || match.equals(""))) { // company not null ,append(company)
            sb.append(company.trim());
            if (!(league == null || league.equals(""))) { // league not null ,append(league)
                sb.append(league.trim());
                if (!(year == null || year.equals(""))) { // year not null ,append(year)
                    sb.append(year.trim());
                    if (!(company == null || company.equals(""))) { // match not null ,append(match)
                        sb.append(company.trim());
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
