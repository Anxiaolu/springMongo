/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.repository;

import cn.edu.sdut.softlab.entity.Data;
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
    public List<Data> findByCompany(String Company) {
        return mongoTemplate.find(Query.query(Criteria.where("company").is(Company)), Data.class);
    }

    @Override
    public List<Data> findByCompanyAndLeague(String Company, String League) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("company").is(Company), Criteria.where("league").is(League));
        return mongoTemplate.find(new Query(criteria), Data.class);
    }

    @Override
    public List<Data> findByCompanyAndLeagueAndYear(String Company, String League, String Year) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("company").is(Company), Criteria.where("league").is(League), Criteria.where("year").is(Year));
        return mongoTemplate.find(new Query(criteria), Data.class);
    }

    @Override
    public Data findOneData(String Company, String League, String Year, String Match) {
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("company").is(Company), Criteria.where("league").is(League),
                Criteria.where("year").is(Year), Criteria.where("match").is(Match));
        return mongoTemplate.findOne(new Query(criteria), Data.class);
    }

    @Override
    public List<Data> findLikeCompany(String company_name) {
        Pattern pattern = Pattern.compile("^.*" + company_name + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("company").regex(pattern));
        List<Data> datas = mongoTemplate.find(query, Data.class, "data");
        return datas;
    }

}
