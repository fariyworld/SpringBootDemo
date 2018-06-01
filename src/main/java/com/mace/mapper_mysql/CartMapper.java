package com.mace.mapper_mysql;

import com.mace.common.BaseMapper;
import com.mace.domain.Cart;

public interface CartMapper extends BaseMapper<Cart> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(Cart record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Cart record);

    /**
     *
     * @mbggenerated
     */
    Cart selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Cart record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Cart record);
}