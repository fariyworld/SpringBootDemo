package com.mace.mapper_mysql;

import com.mace.common.BaseMapper;
import com.mace.domain.OrderItem;

public interface OrderItemMapper extends BaseMapper<OrderItem> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(OrderItem record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(OrderItem record);

    /**
     *
     * @mbggenerated
     */
    OrderItem selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(OrderItem record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(OrderItem record);
}