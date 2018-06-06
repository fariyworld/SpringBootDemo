package com.mace.solr.bean;

import io.lettuce.core.GeoArgs;

/**
 * description:
 * <br />
 * Created by mace on 17:53 2018/6/5.
 */
public class SolrGroupAttribute {

    private boolean isGroup = true; //group                 设为true，表示结果需要分组
    private String field;           //group.field           需要分组的字段
    private String function;        //group.func            查询函数
    private String query;           //group.query           查询语句
    private int limit = 1;          //group.limit           每组返回多数条结果,默认1
    private int offset = 0;         //group.offset          指定每组结果开始位置/偏移量
    private String groupSort;       //group.sort            SortClause/field order控制每一分组内部的顺序,本处使用field order
    private String format;          //group.format          grouped/simple  设置为simple可以使得结果以单一列表形式返回
    private boolean mainGroup;      //group.main            设为true时，结果将主要由第一个字段的分组命令决定
    private boolean ngroups = true; //group.ngroups         设为true时，Solr将返回分组数量，默认fasle
    private boolean truncate;       //group.truncate        设为true时，facet数量将基于group分组中匹相关性高的文档，默认fasle
    private int cache = 0;          //group.cache.percent   设为大于0时，表示缓存结果，默认为0。该项对于布尔查询，通配符查询，模糊查询有改善，却会减慢普通词查询。

    private int rows = 10;          //rows                  返回多少组结果，默认10
    private int start = 0;          //start                 指定结果开始位置/偏移量
    private String sort;            //sort                  SortClause/field order控制各个组的返回顺序,本处使用field order(price:asc)


    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getGroupSort() {
        return groupSort;
    }

    public void setGroupSort(String groupSort) {
        this.groupSort = groupSort;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isMainGroup() {
        return mainGroup;
    }

    public void setMainGroup(boolean mainGroup) {
        this.mainGroup = mainGroup;
    }

    public boolean isNgroups() {
        return ngroups;
    }

    public void setNgroups(boolean ngroups) {
        this.ngroups = ngroups;
    }

    public boolean isTruncate() {
        return truncate;
    }

    public void setTruncate(boolean truncate) {
        this.truncate = truncate;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }
}
