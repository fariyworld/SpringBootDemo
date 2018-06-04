package com.mace.solr.service.impl;

import com.mace.entity.Dept;
import com.mace.solr.service.ISolrService;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * <br />
 * Created by mace on 22:13 2018/6/2.
 */
@Service("iSolrService")
@Slf4j
public class SolrServiceImpl<T,ID> implements ISolrService<T,ID> {

    @Autowired
    private SolrTemplate solrTemplate;


    @Override
    public boolean insert(String collectionName, T t) {

        return isSuccess(solrTemplate.saveBean(collectionName, t));
    }

    @Override
    public T getById(ID id, String collectionName, Class<T> clazz){

        return solrTemplate.getById(collectionName, id, clazz).get();
    }

    @Override
    public boolean importData(String collectionName, Collection<T> collection){

//        服务器内的最大时间执行提交 Duration.ofHours(1)
        return isSuccess(solrTemplate.saveBeans(collectionName, collection));
    }

    @Override
    public Long count(String collectionName, SolrDataQuery query) {

        return solrTemplate.count(collectionName, query);
    }

    @Override
    public Long count(String collectionName) {

        SimpleQuery simpleQuery = new SimpleQuery("*:*");

        Criteria criteria=new Criteria("dname").contains("中心");
        criteria=criteria.and("loc").contains("西安");

        simpleQuery.addCriteria(criteria);

        ScoredPage<Dept> depts = solrTemplate.queryForPage(collectionName, simpleQuery, Dept.class);

        log.info("符合统计的总记录数：{}",depts.getTotalElements());
        log.info("符合统计的总页数：{}",depts.getTotalPages());

        List<Dept> deptList = depts.getContent();

        for(Dept dept:deptList){

            log.info("详细信息：{}",dept);
        }

        return solrTemplate.count(collectionName, simpleQuery);
    }

    @Override
    public ScoredPage<T> findAll(String collectionName, String queryString,
                      Class<T> clazz, Map<String, String> criteriaMaps){

        Query query = new SimpleQuery(queryString);

        Criteria criteria = new Criteria();

        Set<Map.Entry<String, String>> entrySet = criteriaMaps.entrySet();

        for(Map.Entry<String, String> entry : entrySet){

            criteria.and(entry.getKey()).contains(entry.getValue());
        }

        query.addCriteria(criteria);

        ScoredPage<T> ts = solrTemplate.queryForPage(collectionName, query, clazz);

        log.info("符合统计的总记录数：{}",ts.getTotalElements());
        log.info("符合统计的总页数：{}",ts.getTotalPages());

        List<T> tList = ts.getContent();

        for(T t : tList){

            log.info("详细信息：{}",t);
        }

        return ts;
    }




    private static boolean isSuccess(UpdateResponse response){

        if( response.getStatus() == 200 )
            return true;
        else
            return false;
    }
}
