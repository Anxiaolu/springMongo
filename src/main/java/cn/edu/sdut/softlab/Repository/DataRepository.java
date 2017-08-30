/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huanlu
 */
@Repository
public class DataRepository {
    
    static final Logger logger = LoggerFactory.getLogger(DataRepository.class);
    
    @Autowired
    MongoOperations mongo;
    
    public long countData(){
        return mongo.getCollection("Data").count();
    }
    
}
