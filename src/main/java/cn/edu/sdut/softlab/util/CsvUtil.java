/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Odds;
import com.csvreader.CsvWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;
//import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author huanlu
 */
public class CsvUtil {

    /**
     * .
     * ByteArrayOutputStream coverte to byteArray
     * @param datalist
     * @return 
     * @throws IOException 
     */
    public ByteArrayOutputStream process(List<Data> datalist)throws IOException{
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        CsvWriter csvWriter = new CsvWriter(output, ',', Charset.forName("UTF-8"));
            String[] tableheader = {"Company", "League", "Year", "Match", "Win", "Draw", "Lose", "Rate", "UpdateTime"};
            csvWriter.writeRecord(tableheader);
            for (Data data : datalist) {
                    for (Odds odd : data.getOdds()) {
                        if (!(data.getOdds() == null)) {
                            csvWriter.writeRecord(this.getDataOdd(data, odd));
                        }
                    }
            csvWriter.writeRecord(this.getData(data));
            }
            csvWriter.writeRecord(new String[]{"Betano.ro", "英超", "2017", "曼彻斯特城VS利物浦赔率", "1.79", "3.6", "3.85", "0.098", "2017-08-14T01:55:00Z"});
            csvWriter.close();
        
        return output;
    }
    
    public String[] getDataOdd(Data d, Odds o) {
        String[] arry = {d.getCompany(), d.getLeague(), d.getYear(), d.getMatch(),o.getWin(), o.getDraw(), o.getLose(), o.getReturnRate(), o.getUpdateTime()} ;
        return arry;
    }

    public String[] getData(Data d) {
        String[] data = {d.getCompany(), d.getLeague(), d.getYear(), d.getMatch()};
        return data;
    }
}
