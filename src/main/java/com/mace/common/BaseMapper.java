package com.mace.common;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 17:02 2018/4/27.
 */
public interface BaseMapper<T>{

    List<T> findByPage();
}
