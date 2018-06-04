package com.mace.controller.solr;

import com.mace.common.ResponseMessage;
import com.mace.entity.Dept;
import com.mace.solr.service.ISolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
