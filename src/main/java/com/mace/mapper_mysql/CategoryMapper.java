package com.mace.mapper_mysql;

import com.mace.common.BaseMapper;
import com.mace.domain.Category;

public interface CategoryMapper extends BaseMapper<Category> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(Category record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Category record);

    /**
     *
     * @mbggenerated
     */
    Category selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Category record);
}