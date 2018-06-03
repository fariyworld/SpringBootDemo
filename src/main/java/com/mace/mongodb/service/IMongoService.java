package com.mace.mongodb.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * description:
 * <br />
 * Created by mace on 10:10 2018/5/29.
 */
public interface IMongoService<T> {


    /**
     * description: 增 指定表名
     * <br /><br />
     * create by mace on 2018/6/2 22:07.
     * @param t                 bean
     * @param collectionName    表名
     * @return: boolean         suceess: true failed: false
     */
    boolean insert(T t, String collectionName);


    /**
     * description: 批量插入
     * <br /><br />
     * create by mace on 2018/6/3 10:44.
     * @param collection
     * @param collectionName
     * @return: boolean
     */
    boolean batchInsert(Collection<T> collection, String collectionName);



    /**
     * description: 删
     * <br /><br />
     * create by mace on 2018/6/2 22:10.
     * @param filedName         根据 filedName 删
     * @param val               fieldValue
     * @param collectionName    表名
     * @return: boolean
     */
    boolean delete(String filedName, String val, String collectionName);


    /**
     * description: 改
     * <br /><br />
     * create by mace on 2018/6/2 22:10.
     * @param t                 bean
     * @param collectionName    表名
     * @return: boolean
     */
    boolean update(T t, String collectionName);


    /**
     * description: 查 one 指定表名
     * <br /><br />
     * create by mace on 2018/6/2 22:08.
     * @param filedName         根据 filedName 查
     * @param val               fieldValue
     * @param clazz             bean.class
     * @param collectionName    表名
     * @return: T
     */
    T getBean(String filedName, String val,Class<T> clazz, String collectionName);


    /**
     * description: 查询所有
     * <br /><br />
     * create by mace on 2018/6/2 22:11.
     * @param clazz             bean.class
     * @param collectionName    表名
     * @return: java.util.List<T>
     */
    List<T> getBeanList(Class<T> clazz, String collectionName);


    /**
     * description: 获取当前连接的 mongodb
     * <br /><br />
     * create by mace on 2018/6/3 9:53.
     * @param
     * @return: java.lang.String
     */
    String getDb();


    /**
     * description: 获取当前连接数据库下所有的表名（collectionName）
     * <br /><br />
     * create by mace on 2018/6/3 9:55.
     * @param
     * @return: java.util.Set<java.lang.String>
     */
    Set<String> getCollectionNames();


    /**
     * description: 通过 JavaBean 获取表名（collectionName）
     * <br /><br />
     * create by mace on 2018/6/3 9:59.
     * @param clazz
     * @return: java.lang.String
     */
    String getCollectionName(Class<T> clazz);


    /**
     * description: 根据表名（collectionName）查询表（collection）是否存在
     * <br /><br />
     * create by mace on 2018/6/3 10:01.
     * @param collectionName    表名
     * @return: boolean         存在：true
     */
    boolean collectionExists(String collectionName);


    /**
     * description: 根据 JavaBean 查询表（collection）是否存在
     * <br /><br />
     * create by mace on 2018/6/3 10:02.
     * @param clazz     JavaBean.class
     * @return: boolean 存在：true
     */
    boolean collectionExists(Class<T> clazz);


    /**
     * description: 创建表（collection）
     * <br /><br />
     * create by mace on 2018/6/3 10:07.
     * @param collectionName
     * @return: boolean
     */
    boolean createCollection(String collectionName);
}
