package com.mace.mapper_mysql;

import com.mace.common.BaseMapper;
import com.mace.domain.Order;

public interface OrderMapper extends BaseMapper<Order> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(Order record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Order record);

    /**
     *
     * @mbggenerated
     */
    Order selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Order record);
}