package com.mace.solr.service.impl;

import com.mace.solr.service.ISolrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 22:13 2018/6/2.
 */
@Service("iSolrService")
public class SolrServiceImpl<T> implements ISolrService<T> {

    @Autowired
    private SolrTemplate solrTemplate;


    public T getById(String id, String collectionName, Class<T> clazz){

        return solrTemplate.getById(collectionName, id, clazz).get();
    }

    public void importData(String collectionName, List<T> list){

        solrTemplate.saveBeans(collectionName, list, Duration.ofHours(1));
    }
}
