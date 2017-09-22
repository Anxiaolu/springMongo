/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.repository;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Page;
import java.util.List;

/**
 *
 * @author huanlu
 */
public interface DataRepository {
    
    Long count();

    List<Data> findAll();
    
    /**
     * 根据查询条件,查询记录分页信息
     * @param searchModel 封装查询条件
     * @param pageNum     查询第几页数据
     * @param pageSize    每页显示多少条记录
     * @return            查询结果
     */
    public Page<Data> findDataByPage(Data searchModel,int pageNum,int pageSize);
    
    //模糊查询
    List<Data> findLikeCompany(String company_name);

    List<Data> findByCompany(String Company);

    List<Data> findByCompanyAndLeague(String Company, String League);

    List<Data> findByCompanyAndLeagueAndYear(String Company, String League, String Year);

    Data findOneData(String Company, String League, String Year, String Match);
}
