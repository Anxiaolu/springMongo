/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.repository.impl;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Page;
import cn.edu.sdut.softlab.repository.DataPageRepository;
import cn.edu.sdut.softlab.repository.DataRepository;
import java.util.List;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huanlu
 */
@Repository
public class DataRepositoryImpl implements DataRepository {

    static final Logger logger = LoggerFactory.getLogger(DataRepositoryImpl.class);

    @Autowired
    DataPageRepository dataPageRepository;
    
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongo;

    public Long count() {
        return mongo.getCollection("data").count();
    }

    @Override
    public List<Data> findAll() {
        return mongoTemplate.findAll(Data.class, "data");
    }

    @Override
    public List<Data> findByMatch(String  Match) {
        return mongoTemplate.find(Query.query(Criteria.where("match").is(Match)), Data.class);
    }

    @Override
    public List<Data> findByMatchAndLeague(String Match, String League) {
        Criteria criteria = new Criteria();
        Query query = new Query(Criteria.where("match").is(Match).and("league").is(League));
        return mongoTemplate.find(query, Data.class);
    }

    @Override
    public List<Data> findByMatchAndLeagueAndYear(String  Match, String League, String Year) {
        Criteria criteria = new Criteria();
        Query query = new Query(Criteria.where("match").is(Match).and("league").is(League).and("year").is(Integer.parseInt(Year)));
        return mongoTemplate.find(query, Data.class);
    }

    @Override
    public Data findOneData(String Company, String League, String Year, String Match) {
        Integer year = Integer.parseInt(Year);
        Query query = new Query(Criteria.where("company").is(Company).and("league").is(League).and("year").is(Integer.parseInt(Year)).and("match").is(Match));
        return mongoTemplate.findOne(query, Data.class);
    }

    @Override
    public List<Data> findLikeMatch(String match_name) {
        Pattern pattern = Pattern.compile("^.*" + match_name + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("match").regex(pattern));
        List<Data> datas = mongoTemplate.find(query, Data.class, "data");
        return datas;
    }

    /**
     * 分页查询实现方法
     * @param searchModel
     * @param pageNum
     * @param pageSize
     * @return 
     */
    @Override
    public Page<Data> findDataByPage(Data searchModel, int pageNum, int pageSize) {
        List<Data> datas = this.findAll();
        Page<Data> pager = new Page<Data>(pageNum,pageSize,datas);
        return pager;
    }

}
