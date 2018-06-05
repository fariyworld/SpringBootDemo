package com.mace.solr.service.impl;

import com.mace.entity.Dept;
import com.mace.solr.service.ISolrService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.stereotype.Service;

import java.util.*;

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

//------------------------      增      ------------------------

    @Override
    public boolean insert(String collectionName, T t) {

        return isSuccess(solrTemplate.saveBean(collectionName, t));
    }

    @Override
    public boolean importData(String collectionName, Collection<T> collection){

//        服务器内的最大时间执行提交 Duration.ofHours(1)
        return isSuccess(solrTemplate.saveBeans(collectionName, collection));
    }

//------------------------      删      ------------------------

    @Override
    public boolean delete(String collectionName, ID id) {

        return isSuccess(solrTemplate.deleteByIds(collectionName, String.valueOf(id)));
    }

    @Override
    public boolean delete(String collectionName, Collection<ID> ids){

        return isSuccess(solrTemplate.deleteByIds(collectionName, convert(ids)));
    }

    @Override
    public boolean delete(String collectionName, String queryString, Map<String, String> criteriaMaps){

        Query query = assembleSolrQuery(queryString, criteriaMaps);

        return isSuccess(solrTemplate.delete(collectionName, query));
    }

//------------------------      查      ------------------------

    @Override
    public T getById(ID id, String collectionName, Class<T> clazz){

        return solrTemplate.getById(collectionName, id, clazz).get();
    }


    public Collection<T> findAll(String collectionName, Collection<String> ids, Class<T> clazz){

        return solrTemplate.getByIds(collectionName, ids, clazz);
    }


    public Collection<T> findAll(String collectionName, String queryString, Class<T> clazz){

        Query query = new SimpleQuery(queryString);

        return solrTemplate.query(collectionName, query, clazz);
    }

    @Override
    public Long count(String collectionName, String queryString, Map<String, String> criteriaMaps) {

        Query query = assembleSolrQuery(queryString, criteriaMaps);

        return solrTemplate.count(collectionName, query);
    }

    @Override
    public Long count(String collectionName) {

        SimpleQuery simpleQuery = new SimpleQuery("*:*");

        return solrTemplate.count(collectionName, simpleQuery);
    }

    @Override
    public ScoredPage<T> queryForPage(String collectionName, String queryString,
                      Class<T> clazz, Map<String, String> criteriaMaps){

        Query query = assembleSolrQuery(queryString, criteriaMaps);

        ScoredPage<T> ts = solrTemplate.queryForPage(collectionName, query, clazz);

        log.info("符合统计的总记录数：{}",ts.getTotalElements());
        log.info("符合统计的总页数：{}",ts.getTotalPages());

        List<T> tList = ts.getContent();

        for(T t : tList){

            log.info("详细信息：{}",t);
        }

        return ts;
    }

//    @Override
//    public Long count(String collectionName) {
//
//        SimpleQuery simpleQuery = new SimpleQuery("*:*");
//
//        Criteria criteria=new Criteria("dname").contains("中心");
//        criteria=criteria.and("loc").contains("西安");
//
//        simpleQuery.addCriteria(criteria);
//
//        ScoredPage<Dept> depts = solrTemplate.queryForPage(collectionName, simpleQuery, Dept.class);
//
//        log.info("符合统计的总记录数：{}",depts.getTotalElements());
//        log.info("符合统计的总页数：{}",depts.getTotalPages());
//
//        List<Dept> deptList = depts.getContent();
//
//        for(Dept dept:deptList){
//
//            log.info("详细信息：{}",dept);
//        }
//
//        return solrTemplate.count(collectionName, simpleQuery);
//    }

//------------------------      高亮查询      ------------------------
//    Criteria: dept_keywords:keywords 按:分隔， keywords按,分隔
//    theme: em:red 按:分隔， 样式:颜色
    @Override
    public HighlightPage<T> queryForHighlightPage(String collectionName, String queryString,
                                                  Map<String, String> criteriaMaps,
                                                  Class<T> clazz, String theme, String... fieldName){

        HighlightQuery query = ( SimpleHighlightQuery) assembleSolrQuery(queryString, criteriaMaps);

//        HighlightQuery query = new SimpleHighlightQuery();

        //设置高亮的域
        HighlightOptions highlightOptions=new HighlightOptions().addField(fieldName);

        //设置高亮主题
        if (theme == null || StringUtils.isBlank(theme))    theme = "em:red";
        String[] themes = splitKeyValue(theme);
        String prefix = "<" + themes[0] + " style='color:" + themes[1] +"'>";
        String postfix = "</" + themes[0] + ">";
        //高亮前缀
        highlightOptions.setSimplePrefix(prefix);
        //高亮后缀
        highlightOptions.setSimplePostfix(postfix);

        query.setHighlightOptions(highlightOptions);//设置高亮选项

        //高亮查询
        HighlightPage<T> ts = solrTemplate.queryForHighlightPage(collectionName, query, clazz);

        //遍历高亮入口集合
        for(HighlightEntry<T> entry : ts.getHighlighted()){

            //获取实体类
            T t = entry.getEntity();

            //获取高亮列表(高亮域的个数)
            List<HighlightEntry.Highlight> highlightList = entry.getHighlights();

            for(HighlightEntry.Highlight h1:highlightList){

                //每个域有可能存储多值
                List<String> sns = h1.getSnipplets();
                log.info("高亮列表: {}",sns);
            }
        }

        List<T> tList = ts.getContent();

        for (T t : tList) {

            log.info("详细信息：{}",t);
        }

        return ts;
    }



//------------------------      分组查询      ------------------------

    //Criteria: dept_keywords:keywords 按:分隔， keywords按,分隔
    @Override
    public GroupPage<T> queryForGroupPage(String collectionName, String queryString,
                                          Map<String, String> criteriaMaps, String groupField, Class<T> clazz){

        Query query = assembleSolrQuery(queryString, criteriaMaps);

        //设置分组选项
        GroupOptions groupOptions=new GroupOptions().addGroupByField(groupField);
        query.setGroupOptions(groupOptions);

        //得到分组页
        GroupPage<T> ts = solrTemplate.queryForGroupPage(collectionName, query, clazz);

        //根据列得到分组结果集
        GroupResult<T> groupResult = ts.getGroupResult(groupField);

        //得到分组结果入口页
        Page<GroupEntry<T>> groupEntries = groupResult.getGroupEntries();

        //得到分组入口集合
        List<GroupEntry<T>> entryList = groupEntries.getContent();

        for (GroupEntry<T> entry : entryList) {
            //将分组结果的名称封装到返回值中
            log.info("分组结果的名称: {}",entry.getGroupValue());
        }

        return ts;
    }



//------------------------      简单封装      ------------------------

    private static boolean isSuccess(UpdateResponse response){

        if( response.getStatus() == 200 )
            return true;
        else
            return false;
    }

    private static Query assembleSolrQuery(String queryString, Map<String, String> criteriaMaps){

        if(queryString == null || StringUtils.isBlank(queryString)){

            queryString = "*:*";
        }

        Query query = new SimpleQuery(queryString);

        if(criteriaMaps != null && criteriaMaps.size() >0){

            Criteria criteria = new Criteria();

            Set<Map.Entry<String, String>> entrySet = criteriaMaps.entrySet();

            for(Map.Entry<String, String> entry : entrySet){

                criteria.and(entry.getKey()).contains(entry.getValue());
            }

            query.addCriteria(criteria);
        }

        return query;
    }

    //Criteria: dept_keywords:keywords 按:分隔
    private static String[] splitKeyValue(String criteriaStr){

        return criteriaStr.split(":", -1);
    }

    private static <ID> Collection<String> convert(Collection<ID> ids){

        Collection<String> collection = new ArrayList<>();

        Iterator<ID> iterator = ids.iterator();

        while(iterator.hasNext()){

            String id = (String) iterator.next();

            collection.add(id);
        }

        return collection;
    }
}
