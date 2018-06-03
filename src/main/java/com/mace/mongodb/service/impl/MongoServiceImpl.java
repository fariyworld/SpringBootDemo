package com.mace.mongodb.service.impl;

import com.mace.mongodb.service.IMongoService;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * description: Mongo服务类
 * <br />
 * Created by mace on 10:11 2018/5/29.
 */
@Service("iMongoService")
@Slf4j
public class MongoServiceImpl<T> implements IMongoService<T> {

    @Autowired
    private MongoTemplate mongoTemplate;


    public boolean insert(T t, String collectionName){

        try {
            mongoTemplate.insert(t, collectionName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean batchInsert(Collection<T> collection, String collectionName){

        try {
            mongoTemplate.insert(collection, collectionName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public T getBean(String filedName, String val,Class<T> clazz, String collectionName){

        Query query=new Query(Criteria.where(filedName).is(val));

        return mongoTemplate.findOne(query, clazz, collectionName);
    }


    public boolean update(T t, String collectionName){

        try {
            mongoTemplate.save(t, collectionName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean delete(String filedName, String val, String collectionName){

        Query query = new Query(Criteria.where(filedName).is(val));

        return mongoTemplate.remove(query, collectionName).wasAcknowledged();
    }

    public List<T> getBeanList(Class<T> clazz, String collectionName){

        return mongoTemplate.findAll(clazz, collectionName);
    }


    public String getDb(){

        return mongoTemplate.getDb().getName();
    }


    public Set<String> getCollectionNames(){

        return mongoTemplate.getCollectionNames();
    }


    public String getCollectionName(Class<T> clazz){

        return mongoTemplate.getCollectionName(clazz);
    }


    public boolean collectionExists(String collectionName){

        return mongoTemplate.collectionExists(collectionName);
    }


    public boolean collectionExists(Class<T> clazz){

        return mongoTemplate.collectionExists(clazz);
    }


    public boolean createCollection(String collectionName){

        if(collectionExists(collectionName)){
            log.error("表 {} 已存在，创建失败", collectionName);
            return false;
        }else{
            mongoTemplate.createCollection(collectionName);
            return true;
        }
    }

}
