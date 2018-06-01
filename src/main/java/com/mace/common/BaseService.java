package com.mace.common;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * description:
 * <br />
 * Created by mace on 17:21 2018/4/28.
 */
public interface BaseService<T,PK extends Serializable> {


    T selectByPrimaryKey(PK id);

    int deleteByPrimaryKey(PK id);

    int insert(T t);

    int insertSelective(T t);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    PageInfo<T> findByPage(int pageNum, int pageSize);
}
