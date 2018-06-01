package com.mace.mongodb.service.impl;

import com.mace.mongodb.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * description: Mongo服务类
 * <br />
 * Created by mace on 10:11 2018/5/29.
 */
@Service("iMongoService")
public class MongoServiceImpl implements IMongoService {

    @Autowired
    private MongoTemplate mongoTemplate;



}
