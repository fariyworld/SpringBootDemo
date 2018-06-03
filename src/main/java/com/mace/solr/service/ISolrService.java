package com.mace.solr.service;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 22:13 2018/6/2.
 */
public interface ISolrService<T>  {

    T getById(String id, String collectionName, Class<T> clazz);

    void importData(String collectionName, List<T> list);
}
