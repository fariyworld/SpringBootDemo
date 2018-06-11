package com.mace.rabbitmq.service.impl;

import com.google.common.collect.Lists;
import com.mace.controller.mysql.portal.CartController;
import com.mace.domain.Cart;
import com.mace.fastjson.util.FastJsonUtil;
import com.mace.rabbitmq.service.IRabbitMQService;
import com.mace.vo.CartProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * <br />
 * Created by mace on 11:41 2018/6/11.
 */
@Service("iRabbitMQService")
@Slf4j
public class RabbitMQServiceImpl implements IRabbitMQService {

    @Autowired
    private AmqpTemplate rabbitTemplate;


    public void syncOfflineShoppingCart(Map<Object, Object> off_line_shopping_cart, Integer userId) {

        List<Cart> cartList = Lists.newArrayList();

        Set<Map.Entry<Object, Object>> entrySet = off_line_shopping_cart.entrySet();

        for (Map.Entry<Object, Object> entry : entrySet) {

            // entry.getKey();   店铺 ID
            // entry.getValue()  (cartProductVo json序列串)
            String value = (String) entry.getValue();
            CartProductVo cartProductVo = FastJsonUtil.toBean(value, CartProductVo.class);
            // 装配 Cart
            cartList.add(CartController.assembleCartByCartProductVo(cartProductVo, userId));
        }

        rabbitTemplate.convertAndSend("redis_off_line_cart", cartList);
    }

    @RabbitListener(queues = "redis_off_line_cart")
    public void batchInsertCart(List<Cart> cartList){

        // mtbatis

    }




}
