package com.mace.mapper_mysql;

import com.mace.common.BaseMapper;
import com.mace.domain.Product;

public interface ProductMapper extends BaseMapper<Product> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(Product record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Product record);

    /**
     *
     * @mbggenerated
     */
    Product selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Product record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Product record);
}