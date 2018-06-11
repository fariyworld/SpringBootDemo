package com.mace.rabbitmq.service;

import java.util.Map;

/**
 * description:
 * <br />
 * Created by mace on 11:40 2018/6/11.
 */
public interface IRabbitMQService {

    /**
     * description: 发送消息
     *              同步离线购物车数据到 mysql cart 表
     * <br /><br />
     * create by mace on 2018/6/11 11:55.
     * @param off_line_shopping_cart key:shopId value:cartProductVo
     * @return: void
     */
    void syncOfflineShoppingCart(Map<Object, Object> off_line_shopping_cart, Integer userId);
}
