package com.mace.solr.service;

import com.mace.solr.bean.SolrGroupAttribute;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
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


//------------------------      增      ------------------------

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


//------------------------      删      ------------------------

    /**
     * description: 根据 id 至指定 core 删除
     * <br /><br />
     * create by mace on 2018/6/4 13:00.
     * @param collectionName
     * @param id
     * @return: boolean
     */
    boolean delete(String collectionName, ID id);


    /**
     * description: 根据 query 至指定 core 删除
     * <br /><br />
     * create by mace on 2018/6/4 13:15.
     * @param collectionName
     * @param queryString   查询串 默认 *:*
     * @param criteriaMaps  条件 没有可传 null 或者空集合
     * @return: boolean
     */
    boolean delete(String collectionName, String queryString, Map<String, String> criteriaMaps);

    /**
     * description: 根据 ids 至指定 core 批量删除
     * <br /><br />
     * create by mace on 2018/6/4 13:04.
     * @param collectionName
     * @param ids
     * @return: boolean
     */
    boolean delete(String collectionName, Collection<ID> ids);


//------------------------      查      ------------------------

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
     * description: 根据 ids 去指定 core 批量查询
     * <br /><br />
     * create by mace on 2018/6/4 13:29.
     * @param collectionName
     * @param ids
     * @param clazz
     * @return: java.util.Collection<T>
     */
    Collection<T> getByIds(String collectionName, Collection<String> ids, Class<T> clazz);


    /**
     * description: 条件查询
     * <br /><br />
     * create by mace on 2018/6/5 11:20.
     * @param collectionName
     * @param queryString
     * @param criteriaMaps
     * @param clazz
     * @return: java.util.Collection<T>
     */
    Collection<T> getByCriteria(String collectionName, String queryString,
                          Map<String, String> criteriaMaps, Class<T> clazz);


    /**
     * description: 根据 query 统计指定 core 总数
     * <br /><br />
     * create by mace on 2018/6/4 13:21.
     * @param collectionName
     * @param queryString   查询串 默认 *:*
     * @param criteriaMaps  条件 没有可传 null 或者空集合
     * @return: java.lang.Long
     */
    Long count(String collectionName, String queryString, Map<String, String> criteriaMaps);


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
    ScoredPage<T> queryForPage(String collectionName, String queryString,
                          Class<T> clazz, Map<String, String> criteriaMaps);



    /**
     * description: 高亮查询 默认高亮选项 红色 斜体 <em style='color:red'></em>
     * <br /><br />
     * create by mace on 2018/6/4 16:09.
     * @param collectionName
     * @param criteriaMaps
     * @param clazz
     * @param theme         theme: em:red 按:分隔， 样式:颜色
     * @param fieldName
     * @return: org.springframework.data.solr.core.query.result.HighlightPage<T>
     */
    HighlightPage<T> queryForHighlightPage(String collectionName, Map<String, String> criteriaMaps,
                                           Class<T> clazz, String theme, String... fieldName);


    /**
     * description: 分组查询 还未成功
     * <br /><br />
     * create by mace on 2018/6/5 8:49.
     * @param collectionName
     * @param queryString
     * @param criteriaMaps
     * @param groupField
     * @param clazz
     * @return: org.springframework.data.solr.core.query.result.GroupPage<T>
     */
    GroupPage<T> queryForGroupPage1(String collectionName, String queryString,
                                   Map<String, String> criteriaMaps, String groupField, Class<T> clazz);


    GroupPage<T> queryForGroupPage2(String collectionName, String queryString,
                                    Map<String, String> criteriaMaps, String groupField, Class<T> clazz);


    Map<String, List<T>> queryForGroupPage(String collectionName, String queryString,
                                           SolrGroupAttribute attribute, Class<T> clazz);
}
