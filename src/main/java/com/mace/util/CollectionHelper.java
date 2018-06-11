package com.mace.util;

import java.util.Map;
import java.util.Set;

/**
 * description: 集合相关工具类 ergod
 * <br />
 * Created by mace on 10:15 2018/6/11.
 */
public class CollectionHelper {

    /**
     * description: 简单遍历Map 并输出 key : value
     * <br /><br />
     * create by mace on 2018/6/11 10:51.
     * @param map
     * @return: void
     */
    public static <K, V> void iterSimpleMap(Map<K, V> map){

        Set<Map.Entry<K, V>> entrySet = map.entrySet();

        for (Map.Entry<K, V> entry : entrySet) {

            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
