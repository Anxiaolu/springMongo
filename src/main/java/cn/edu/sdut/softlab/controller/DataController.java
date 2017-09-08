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
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    @RequestMapping(value = "/getleagues", method = RequestMethod.POST)
    @ResponseBody
    public Object getLeaguesByCompany(@RequestParam("company_name") String Company_name) {
        List<Data> datas = dataRepository.findByCompany(Company_name);
        List<String> leagues = new ArrayList<>();
        for (Data data : datas) {
            if (!(data.getCompany().equals(""))) {
                leagues.add(data.getLeague());
            }
        }
        HashSet h = new HashSet(leagues);
        leagues.clear();
        leagues.addAll(h);
        return leagues;
    }

    @RequestMapping(value = "/getdata", method = RequestMethod.POST)
    @ResponseBody
    public Object getDatas(@RequestParam("company") String Company,
                            @RequestParam(name = "league",required = false) String league,
                            @RequestParam(name = "Year" ,required = false) String Year,
                            @RequestParam(name = "Match",required = false) String Match) {
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

    @RequestMapping(value = "/findPost", method = RequestMethod.POST)
    public void WriteData(@RequestParam("company") String Company,
            @RequestParam("league") String League,
            @RequestParam("year") String Year,
            @RequestParam("match") String Match) {
        List<Data> datas = this.getDataList(Company, League, Year, Match);
        CsvUtil csv = new CsvUtil();
        if (Company.equals("")
                && League.equals("") || League == null
                && Year.equals("") || Year == null
                && Match.equals("") || Match == null) {
            String MatchMessage = "所有记录";
            csv.wirte(MatchMessage, datas);
        }
        String MatchMessage = Company + League + Year + Match;
        csv.wirte(MatchMessage, datas);
    }
    
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,
             Model model)throws Exception {

        String filename = "download.csv";
        //下载文件路径
        String path = request.getServletContext().getRealPath("/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();  
        //下载显示的文件名，解决中文名称乱码问题  
//        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
        String downloadFielName = new String(filename.getBytes("UTF-8"),"UTF-8");
        //通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFielName); 
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                headers, HttpStatus.CREATED);  
     }
}
