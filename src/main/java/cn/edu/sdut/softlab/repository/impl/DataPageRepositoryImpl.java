/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.repository.impl;

import cn.edu.sdut.softlab.entity.Data;
import cn.edu.sdut.softlab.entity.Page;
import cn.edu.sdut.softlab.repository.DataPageRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author huanlu
 */
@Repository
public class DataPageRepositoryImpl implements DataPageRepository{

    @Override
    public Page<Data> findData(Data searchModel, int pageNum, int pageSize) {
        return null;
    }
    
}
