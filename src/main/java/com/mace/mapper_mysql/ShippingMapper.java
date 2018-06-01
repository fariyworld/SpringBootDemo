package com.mace.mapper_mysql;

import com.mace.common.BaseMapper;
import com.mace.domain.Shipping;

public interface ShippingMapper extends BaseMapper<Shipping> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(Shipping record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Shipping record);

    /**
     *
     * @mbggenerated
     */
    Shipping selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Shipping record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Shipping record);
}