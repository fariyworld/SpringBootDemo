package com.mace.mapper_oracle;

import com.mace.common.BaseMapper;
import com.mace.entity.Dept;

public interface DeptMapper extends BaseMapper<Dept> {
    /**
     *
     * @mbggenerated
     */
    int insert(Dept record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(Dept record);

}