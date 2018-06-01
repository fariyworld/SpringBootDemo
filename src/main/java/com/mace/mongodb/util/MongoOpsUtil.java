package com.mace.mongodb.util;

import org.bson.types.ObjectId;

import java.util.Date;

/**
 * description:
 * <br />
 * Created by mace on 21:13 2018/5/27.
 */
public class MongoOpsUtil {

    /**
     * description: 当前系统时间 --> ObjectId
     * <br /><br />
     * create by mace on 2018/5/27 21:21.
     * @param
     * @return: org.bson.types.ObjectId
     */
    public static ObjectId time2Id(){

        return new ObjectId();
    }

    /**
     * description: 当前系统时间 --> ObjectId --> String
     * <br /><br />
     * create by mace on 2018/5/27 21:21.
     * @param
     * @return: java.lang.String
     */
    public static String ObjectId2Str(){

        return new ObjectId().toString();
    }

    /**
     * description: date --> ObjectId
     * <br /><br />
     * create by mace on 2018/5/27 21:22.
     * @param date  指定date
     * @return: org.bson.types.ObjectId
     */
    public static ObjectId time2Id(Date date){

        return new ObjectId(date);
    }

    /**
     * description: 把MongoDB中的 ObjectId(_id) ---> java.util.Date
     *
     * objectid 前八位是时间戳 16进制 转化为 10进制(s) * 1000(ms)
     * <br /><br />
     * create by mace on 2018/5/27 21:22.
     * @param id  ObjectId(_id)
     * @return: java.util.Date
     */
    public static Date id2time(String id){

        return new Date(Long.parseLong(id.substring(0, 8), 16) * 1000);
    }
}
