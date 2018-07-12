package com.mace.redis.service;

/**
 * description: Redis分布式锁接口
 * <br />
 * Created by mace on 17:22 2018/7/12.
 */
public interface RedisDistributionLock {

    /**
     * description: 加锁成功，返回加锁时间
     * <br /><br />
     * create by mace on 2018/7/12 17:23.
     * @param lockKey
     * @param threadName
     * @return: long
     */
    long lock(String lockKey, String threadName);


    /**
     * description: 解锁，需要更新加锁时间，判断是否有权限
     * <br /><br />
     * create by mace on 2018/7/12 17:23.
     * @param lockKey
     * @param lockValue
     * @param threadName
     * @return: void
     */
    void unlock(String lockKey, long lockValue, String threadName);


    /**
     * description: 多服务器集群，使用下面的方法，
     * 代替System.currentTimeMillis()，获取redis时间，避免多服务的时间不一致问题！！！
     * <br /><br />
     * create by mace on 2018/7/12 17:23.
     * @param
     * @return: long
     */
    long currtTimeForRedis();
}
