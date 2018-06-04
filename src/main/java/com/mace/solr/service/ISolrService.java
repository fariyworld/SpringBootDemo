package com.mace.solr.service;

import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * description:
 * <br />
 * Created by mace on 22:13 2018/6/2.
 */
public interface ISolrService<T,ID>  {


    /**
     * description: 插入数据至指定 core
     * <br /><br />
     * create by mace on 2018/6/4 11:38.
     * @param collectionName
     * @param t
     * @return: boolean
     */
    boolean insert(String collectionName, T t);


    /**
     * description: 批量插入数据至指定 core
     * <br /><br />
     * create by mace on 2018/6/4 10:12.
     * @param collectionName
     * @param collection
     * @return: void boolean
     */
    boolean importData(String collectionName, Collection<T> collection);





    /**
     * description: 根据 ID 去指定 core 查询 bean
     * <br /><br />
     * create by mace on 2018/6/4 10:11.
     * @param id
     * @param collectionName
     * @param clazz
     * @return: T
     */
    T getById(ID id, String collectionName, Class<T> clazz);


    /**
     * description: 根据 query 统计指定 core 总数
     * <br /><br />
     * create by mace on 2018/6/4 10:14.
     * @param collectionName
     * @param query
     * @return: java.lang.Long
     */
    Long count(String collectionName, SolrDataQuery query);


    /**
     * description: 统计指定 core 总记录数
     * <br /><br />
     * create by mace on 2018/6/4 10:40.
     * @param collectionName
     * @return: java.lang.Long
     */
    Long count(String collectionName);


    /**
     * description: 分页查询
     * 根据 queryString、criteriaMaps 到 core 查询指定 bean 的集合
     * <br /><br />
     * create by mace on 2018/6/4 12:01.
     * @param collectionName
     * @param queryString
     * @param clazz
     * @param criteriaMaps
     * @return: org.springframework.data.solr.core.query.result.ScoredPage<T>
     */
    ScoredPage<T> findAll(String collectionName, String queryString,
                          Class<T> clazz, Map<String, String> criteriaMaps);
}
