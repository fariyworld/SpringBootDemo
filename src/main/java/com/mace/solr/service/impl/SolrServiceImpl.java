package com.mace.solr.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mace.solr.bean.SolrGroupAttribute;
import com.mace.solr.service.ISolrService;
import io.lettuce.core.GeoArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.GroupParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
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

        return isSuccess(solrTemplate.delete(collectionName, assembleSolrQuery(queryString, criteriaMaps)));
    }

//------------------------      查      ------------------------

    @Override
    public T getById(ID id, String collectionName, Class<T> clazz){

        return solrTemplate.getById(collectionName, id, clazz).get();
    }

    @Override
    public Collection<T> getByIds(String collectionName, Collection<String> ids, Class<T> clazz){

        return solrTemplate.getByIds(collectionName, ids, clazz);
    }

    @Override
    public Collection<T> getByCriteria(String collectionName, String queryString,
                                 Map<String, String> criteriaMaps, Class<T> clazz){

        return solrTemplate.query(collectionName, assembleSolrQuery(queryString, criteriaMaps), clazz);
    }

    @Override
    public Long count(String collectionName, String queryString, Map<String, String> criteriaMaps) {

        return solrTemplate.count(collectionName, assembleSolrQuery(queryString, criteriaMaps));
    }

    @Override
    public Long count(String collectionName) {

        return solrTemplate.count(collectionName, assembleSolrQuery(null, null));
    }

    @Override
    public ScoredPage<T> queryForPage(String collectionName, String queryString,
                      Class<T> clazz, Map<String, String> criteriaMaps){

        ScoredPage<T> ts = solrTemplate.queryForPage(collectionName,
                                                     assembleSolrQuery(queryString, criteriaMaps),
                                                     clazz);

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
//    theme: em:red 按:分隔， 样式:颜色
    @Override
    public HighlightPage<T> queryForHighlightPage(String collectionName,Map<String, String> criteriaMaps,
                                                  Class<T> clazz, String theme, String... fieldName){

        HighlightQuery query = new SimpleHighlightQuery();

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

        //设置高亮选项
        query.setHighlightOptions(highlightOptions);

        //条件查询
        addCriteria(query, criteriaMaps);

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
    public GroupPage<T> queryForGroupPage1(String collectionName, String queryString,
                                          Map<String, String> criteriaMaps, String groupField, Class<T> clazz){

        Query groupQuery = assembleSolrQuery(queryString, criteriaMaps);

        //Pageable
        Pageable pageable = new SolrPageRequest(1, 10);
        groupQuery.setPageRequest(pageable);

        //设置分组选项
        GroupOptions groupOptions=new GroupOptions().addGroupByField(groupField);

        groupQuery.setGroupOptions(groupOptions);

        //得到分组页
        GroupPage<T> ts = solrTemplate.queryForGroupPage(collectionName, groupQuery, clazz);

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

    @Override
    public GroupPage<T> queryForGroupPage2(String collectionName, String queryString,
                                          Map<String, String> criteriaMaps, String groupField, Class<T> clazz){


        Function func = ExistsFunction.exists("loc");
        Query query = new SimpleQuery("*:*");

        SimpleQuery groupQuery = new SimpleQuery(new SimpleStringCriteria("*:*"));
        GroupOptions groupOptions = new GroupOptions()
                .addGroupByField(groupField)
                .addGroupByFunction(func)
                .addGroupByQuery(query);
        groupQuery.setGroupOptions(groupOptions);

        GroupPage<T> page = solrTemplate.queryForGroupPage(collectionName, groupQuery, clazz);

        GroupResult<T> groupResult = page.getGroupResult(groupField);

        return page;
    }

    //分组查询
    @Override
    public Map<String, List<T>> queryForGroupPage(String collectionName, String queryString,
                                                  SolrGroupAttribute attribute, Class<T> clazz) {

        Map<String, List<T>> map = Maps.newHashMap();

        SolrClient solrClient = solrTemplate.getSolrClient();

        QueryResponse response = null;

        try {

            response = solrClient.query(collectionName, assembleSolrGroupQuery(queryString,attribute));

            if(response.getStatus() == 0){

                GroupResponse groupResponse = response.getGroupResponse();

                List<GroupCommand> values = groupResponse.getValues();

                for (GroupCommand value : values) {

                    List<Group> groups = value.getValues();

                    for (Group group : groups) {

                        String groupValue = group.getGroupValue();

//                        log.info("分组信息：{}",groupValue);

                        SolrDocumentList results = group.getResult();

                        map.put(groupValue, convertSolrDocementToBeanList(results, clazz));

//                        for (SolrDocument result : results) {
//
//                            log.info("solrDocument: {}",result);
//                        }
                    }
                }
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                solrClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

//------------------------      简单封装      ------------------------

    private static boolean isSuccess(UpdateResponse response){

        if( response.getStatus() == 0 )
            return true;
        else
            return false;
    }

    private static Criteria assembleCriteria(Map<String, String> criteriaMaps){

        Criteria criteria = null;

        if(criteriaMaps != null && criteriaMaps.size() >0){

            criteria = new Criteria();

            Set<Map.Entry<String, String>> entrySet = criteriaMaps.entrySet();

            for(Map.Entry<String, String> entry : entrySet){

                criteria.and(entry.getKey()).contains(entry.getValue());
            }
        }

        return criteria;
    }


    private static Query addCriteria(Query query, Map<String, String> criteriaMaps){

        if(assembleCriteria(criteriaMaps) == null)      return query;

        query.addCriteria(assembleCriteria(criteriaMaps));

        return query;
    }


    private static Query assembleSolrQuery(String queryString, Map<String, String> criteriaMaps){

        if(queryString == null || StringUtils.isBlank(queryString))     queryString = "*:*";

        return addCriteria(new SimpleQuery(queryString), criteriaMaps);
    }


    private static SolrQuery assembleSolrGroupQuery(String queryString, SolrGroupAttribute attribute/*String groupField*/){

        SolrQuery query = new SolrQuery(queryString);

        query.set(GroupParams.GROUP,true);

        //把SolrGroupAttribute中属性不为null的属性值放到SolrQuery中

        String field = attribute.getField();
        if(field != null && StringUtils.isNotBlank(field))              query.set(GroupParams.GROUP_FIELD,field);

        String function = attribute.getFunction();
        if(function != null && StringUtils.isNotBlank(function))        query.set(GroupParams.GROUP_FUNC,function);

        String groupQuery = attribute.getQuery();
        if(groupQuery != null && StringUtils.isNotBlank(groupQuery))    query.set(GroupParams.GROUP_QUERY,groupQuery);

        query.set("rows",attribute.getRows());
        query.set("start",attribute.getStart());
        query.set(GroupParams.GROUP_LIMIT,attribute.getLimit());
        query.set(GroupParams.GROUP_OFFSET,attribute.getOffset());

        String sort = attribute.getSort();
        if(sort != null && StringUtils.isNotBlank(sort))                query.setParam("sort",sort);

        String groupSort = attribute.getGroupSort();
        if(groupSort != null && StringUtils.isNotBlank(groupSort))      query.setParam(GroupParams.GROUP_SORT,groupSort);

        String format = attribute.getFormat();
        if(format != null && StringUtils.isNotBlank(format))            query.setParam(GroupParams.GROUP_FORMAT,format);

        query.setParam(GroupParams.GROUP_MAIN,attribute.isMainGroup());
        query.setParam(GroupParams.GROUP_TOTAL_COUNT,attribute.isNgroups());
        query.setParam(GroupParams.GROUP_TRUNCATE,attribute.isTruncate());
        query.set(GroupParams.GROUP_CACHE_PERCENTAGE,attribute.getCache());

//        需要分组的字段
//        query.set("group.field",groupField);
//        设为true，表示结果需要分组
//        query.set("group",true);
//        设为true时，Solr将返回分组数量，默认fasle
//        query.set("group.ngroups",true);
//        每组返回多数条结果,默认1
//        query.set("group.limit",10);

        return query;
    }

    //Criteria: dept_keywords:keywords 按:分隔
    private static String[] splitKeyValue(String criteriaStr){

        return criteriaStr.split(":", -1);
    }

    private static <ID> Collection<String> convert(Collection<ID> ids){

        Collection<String> collection = Sets.newHashSet();

        Iterator<ID> iterator = ids.iterator();

        while(iterator.hasNext()){

            String id = (String) iterator.next();

            collection.add(id);
        }

        return collection;
    }


    private static <T> T convertSolrDocementToBean(SolrDocument solrDocument, Class<T> clazz){

        T t = null;

        try {

            t = clazz.newInstance();

            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {

                Object value = null;

                if("serialVersionUID".equals(field.getName()))      continue;

                if("id".equals(field.getName()))
                    value = Integer.valueOf(solrDocument.get(field.getName()).toString());
                else
                    value = solrDocument.get(field.getName());

                //获取此对象的可访问标志的值
                boolean flag = field.isAccessible();
                //设置为允许访问  取消封装
                field.setAccessible(true);
                //obj：应该修改字段的对象  value：为正在修改的obj的字段赋值
                field.set(t, value);
                //设置回原来的值
                field.setAccessible(flag);
            }

        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        return t;
    }

    private static <T> List<T> convertSolrDocementToBeanList(SolrDocumentList documents, Class<T> clazz){

        List<T> list = Lists.newArrayList();

        for (SolrDocument document : documents) {

            list.add(convertSolrDocementToBean(document, clazz));
        }

        return list;
    }
}
