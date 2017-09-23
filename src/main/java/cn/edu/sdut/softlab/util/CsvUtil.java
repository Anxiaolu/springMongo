/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Odds;
import com.csvreader.CsvWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 *
 * @author huanlu
 */
public class CsvUtil {

    /**
     * .
     *
     * @param datalist
     * @param output
     * @throws IOException
     */
    public void process(List<Data> datalist, OutputStream output) throws IOException {
        CsvWriter csvWriter = new CsvWriter(output, ',', Charset.forName("GB2312"));//文件内字符中文解决,勿改
        String[] tableheader = {"Company", "League", "Year", "Match", "Win", "Draw", "Lose", "Rate", "UpdateTime"};
        csvWriter.writeRecord(tableheader);
        for (Data data : datalist) {
            for (Odds odd : data.getOdds()) {
                csvWriter.writeRecord(this.getDataOdd(data, odd));
            }
        }
        csvWriter.close();
        output.flush();
        output.close();
    }

    public String[] getDataOdd(Data d, Odds o) {
        DateUtil dateUtil = new DateUtil();
        String[] arry = {d.getCompany(), d.getLeague(), d.getYear(), d.getMatch(), o.getWin(), o.getDraw(),
            o.getLose(), o.getReturnrate().toString(), dateUtil.dateToStrLong(o.getUpdatetime())};
        return arry;
    }

    public String[] getData(Data d) {
        String[] data = {d.getCompany(), d.getLeague(), d.getYear(), d.getMatch()};
        return data;
    }
}
