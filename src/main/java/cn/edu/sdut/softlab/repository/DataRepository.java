/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.repository;

import cn.edu.sdut.softlab.entity.Data;
import java.util.List;

/**
 *
 * @author huanlu
 */
public interface DataRepository {

    List<Data> findAll();
    
    //模糊查询
    List<Data> findLikeCompany(String company_name);

    List<Data> findByCompany(String Company);

    List<Data> findByCompanyAndLeague(String Company, String League);

    List<Data> findByCompanyAndLeagueAndYear(String Company, String League, String Year);

    Data findOneData(String Company, String League, String Year, String Match);
}
