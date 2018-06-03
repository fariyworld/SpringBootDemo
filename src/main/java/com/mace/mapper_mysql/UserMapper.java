package com.mace.mapper_mysql;


import com.mace.common.BaseMapper;
import com.mace.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int insert(User record);

    /**
     *
     * @mbggenerated
     */
    int insertSelective(User record);

    /**
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(Integer id);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);

    @Override
    List<User> findByPage();


    /**
     * description: 检查用户名是否存在
     * <br /><br />       
     * create by mace on 2018/5/3 11:36.
     * @param username  
     * @return: int
     */
    int checkUserName(@Param("username") String username);


    /**
     * description: 检查邮箱是否存在
     * <br /><br />
     * create by mace on 2018/5/3 11:36.
     * @param email
     * @return: int
     */
    int checkEmail(@Param("email") String email);


    /**
     * description: 登录验证
     * <br /><br />
     * create by mace on 2018/6/3 10:38.
     * @param username
     * @param password
     * @return: com.mace.domain.User
     */
    User login(@Param("username") String username, @Param("password") String password);
}