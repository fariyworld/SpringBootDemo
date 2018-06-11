package com.mace.redis.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * <br />
 * Created by mace on 16:57 2018/6/2.
 */
public interface IRedisService {

/***************************  String  *********************************/

    /**
     * description: 设置 String 类型 key-value
     * <br /><br />
     * create by mace on 2018/6/2 17:51.
     * @param key
     * @param value
     * @return: void
     */
    void set(String key,String value);


    /**
     * description: 设置 String 类型 key-value 并添加过期时间 (秒单位)
     * <br /><br />
     * create by mace on 2018/6/2 17:52.
     * @param key
     * @param value
     * @param time   过期时间,秒单位
     * @return: void
     */
    void setForTimeSeconds(String key,String value,long time);


    /**
     * description: 设置 String 类型 key-value 并添加过期时间 (分钟单位)
     * <br /><br />
     * create by mace on 2018/6/2 17:54.
     * @param key
     * @param value
     * @param time  过期时间,分钟单位
     * @return: void
     */
    void setForTimeMIN(String key,String value,long time);


    /**
     * description: 设置 String 类型 key-value 并添加过期时间 (自定义单位)
     * <br /><br />
     * create by mace on 2018/6/2 17:56.
     * @param key
     * @param value
     * @param time  过期时间,自定义单位
     * @param type
     * @return: void
     */
    void setForTimeCustom(String key,String value,long time,TimeUnit type);


    /**
     * description: 如果 key 存在则覆盖,并返回旧值.
     *              如果不存在,返回null 并添加
     * <br /><br />
     * create by mace on 2018/6/2 17:58.
     * @param key
     * @param value
     * @return: java.lang.String
     */
    String getAndSet(String key,String value);


    /**
     * description: 批量添加 key-value (重复的键会覆盖)
     * <br /><br />
     * create by mace on 2018/6/2 17:59.
     * @param keyAndValue
     * @return: void
     */
    void batchSet(Map<String,String> keyAndValue);


    /**
     * description: 批量添加 key-value 只有在键不存在时,才添加
     *              map 中只要有一个key存在,则全部不添加
     * <br /><br />
     * create by mace on 2018/6/2 18:00.
     * @param keyAndValue
     * @return: void
     */
    void batchSetIfAbsent(Map<String,String> keyAndValue);

    /**
     * description:  给一个指定的 key 值附加过期时间
     * <br /><br />
     * create by mace on 2018/6/2 18:18.
     * @param key
     * @param time
     * @param type
     * @return: boolean
     */
    boolean expire(String key,long time,TimeUnit type);


    /**
     * description: 移除指定key 的过期时间
     * <br /><br />
     * create by mace on 2018/6/2 18:19.
     * @param key
     * @return: boolean
     */
    boolean persist(String key);


    /**
     * description: 获取指定key 的过期时间
     * <br /><br />
     * create by mace on 2018/6/2 18:21.
     * @param key
     * @return: java.lang.Long
     */
    Long getExpire(String key);


    /**
     * description:  修改 key
     * <br /><br />
     * create by mace on 2018/6/2 18:21.
     * @param key
     * @param newKey
     * @return: void
     */
    void rename(String key,String newKey);


    /**
     * description:  删除 key-value
     * <br /><br />
     * create by mace on 2018/6/2 18:22.
     * @param key
     * @return: boolean
     */
    boolean delete(String key);

    /**
     * description: 获取 String 类型 key-value
     * <br /><br />
     * create by mace on 2018/6/2 17:50.
     * @param key
     * @return: java.lang.String
     */
    String getStrValByStrKey(String key);


/***************************  hash  *********************************/


    /**
     * description: 添加 Hash 键值对
     * <br /><br />
     * create by mace on 2018/6/2 18:27.
     * @param key
     * @param hashKey
     * @param value
     * @return: void
     */
    void put(String key, String hashKey, String value);


    /**
     * description: 批量添加 hash 的键值对
     *              有则覆盖,没有则添加
     * <br /><br />
     * create by mace on 2018/6/2 18:28.
     * @param key
     * @param map
     * @return: void
     */
    void putAll(String key,Map<String,String> map);


    /**
     * description: 添加 hash 键值对. 不存在的时候才添加
     * <br /><br />
     * create by mace on 2018/6/2 18:33.
     * @param key
     * @param hashKey
     * @param value
     * @return: boolean 仅当 hashKey 不存在时才设置散列 hashKey 的值
     */
    boolean putIfAbsent(String key, String hashKey, String value);


    /**
     * description: 添加 hash 键值对. 不存在的时候才添加
     * <br /><br />
     * create by mace on 2018/6/8 18:01.
     * @param key
     * @param hashKey
     * @param value
     * @return: boolean 仅当 hashKey 不存在时才设置散列 hashKey 的值
     */
    boolean putIfAbsent(String key, String hashKey, long value);


    /**
     * description: 验证指定 key 下 有没有指定的 hashkey
     * <br /><br />
     * create by mace on 2018/6/8 17:53.
     * @param key       outerkey
     * @param hashKey   innerkey
     * @return: boolean
     */
    boolean hashKey(String key,String hashKey);


    /**
     * description: 给指定 hash 的 hashkey 做增减操作
     * <br /><br />
     * create by mace on 2018/6/8 18:02.
     * @param key
     * @param hashKey
     * @param number
     * @return: java.lang.Long
     */
    Long increment(String key, String hashKey,long number);


    /**
     * description: 获取指定 key 下的 hashKey 的 value
     * <br /><br />
     * create by mace on 2018/6/9 15:33.
     * @param key
     * @param hashKey
     * @return: java.lang.Object
     */
    Object getHashKey(String key, String hashKey);


    /**
     * description: 获取指定 key 下的 所有 hashKey、hashValue
     * <br /><br />
     * create by mace on 2018/6/11 11:36.
     * @param key
     * @return: java.util.Map<java.lang.Object,java.lang.Object>
     */
    Map<Object,Object> getHashEntries(String key);
}
