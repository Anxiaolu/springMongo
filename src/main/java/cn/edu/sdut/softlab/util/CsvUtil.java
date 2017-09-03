/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Odds;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author huanlu
 */
public class CsvUtil {
    
    public void wirte(String MatchMessage,List<Data> datalist){
        try {
            File f = new File("/home/huanlu/git_coding/" + MatchMessage + ".csv");
            OutputStream output = new FileOutputStream(f);
            CsvWriter csvWriter = new CsvWriter(output,',',Charset.forName("UTF-8"));
            String[] tableheader = {"Company","League","Year","Match","Win","Draw","Lose","Rate","UpdateTime"};
            csvWriter.writeRecord(tableheader);
            for (Data data : datalist) {
                for (Odds odd: data.getOdds()) {
                    csvWriter.writeRecord(getDataOdd(data, odd));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String[] getDataOdd(Data d,Odds o){
        String[] data = {d.getCompany(),d.getLeague(),d.getYear(),d.getMatch()};
        String[] Odd = {String.valueOf(o.getWin()),String.valueOf(o.getDraw()),String.valueOf(o.getLose()),String.valueOf(o.getReturnRate()),o.getUpdateTime().toString()};
        return  (String[]) ArrayUtils.addAll(data,Odd); 
    }
}
