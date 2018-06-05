package com.mace.controller.solr;

import com.google.common.collect.Maps;
import com.mace.common.ResponseMessage;
import com.mace.entity.Dept;
import com.mace.solr.service.ISolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * description:
 * <br />
 * Created by mace on 14:07 2018/6/3.
 */
@RestController
@RequestMapping("/solr")
public class SolrController {

    @Autowired
    private ISolrService<Dept,String> iSolrService;

    @RequestMapping(value = "getById.do/{id}")
    public ResponseMessage<Dept> set(@PathVariable String id, String collectionName){

        Dept dept = iSolrService.getById(id, collectionName, Dept.class);

        return ResponseMessage.createBySuccess(dept);
    }

    @RequestMapping(value = "count.do/{collectionName}")
    public ResponseMessage<Long> count(@PathVariable String collectionName){

        return ResponseMessage.createBySuccess(iSolrService.count(collectionName));
    }


    @RequestMapping(value = "queryForHighlightPage.do/{collectionName}")
    public ResponseMessage<HighlightPage<Dept>> queryForHighlightPage(@PathVariable String collectionName,
                                                        String criteriaKey, String criteriaValue,
                                                        String theme, String filedName){

        Map<String,String> criteriaMaps = Maps.newHashMap();

        criteriaMaps.put(criteriaKey, criteriaValue);

        HighlightPage<Dept> deptHighlightPage = iSolrService.queryForHighlightPage(collectionName, null,
                criteriaMaps, Dept.class, theme, filedName);

        return ResponseMessage.createBySuccess(deptHighlightPage);
    }


    @RequestMapping(value = "queryForGroupPage.do/{collectionName}")
    public ResponseMessage<GroupPage<Dept>> queryForGroupPage(@PathVariable String collectionName,
                                                                      String criteriaKey,
                                                                      String criteriaValue,
                                                                      String groupField){

        Map<String,String> criteriaMaps = Maps.newHashMap();

        criteriaMaps.put(criteriaKey, criteriaValue);

        GroupPage<Dept> deptGroupPage = iSolrService.queryForGroupPage(collectionName, null,
                criteriaMaps, groupField, Dept.class);

        return ResponseMessage.createBySuccess(deptGroupPage);
    }

}
