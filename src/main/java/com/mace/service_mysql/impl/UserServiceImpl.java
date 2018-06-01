package com.mace.service_mysql.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mace.MD5.MD5Util;
import com.mace.common.Constant;
import com.mace.common.ResponseCode;
import com.mace.common.ResponseMessage;
import com.mace.domain.User;
import com.mace.mapper_mysql.UserMapper;
import com.mace.service_mysql.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 15:54 2018/4/28.
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(Integer id) {

        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public int insertSelective(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return 0;
    }

    /**
     * description: 分页查询
     * <br /><br />
     * create by mace on 2018/5/7 10:44.
     * @param pageNum
     * @param pageSize
     * @return: com.github.pagehelper.PageInfo<com.bonc.domain.User>
     */
    @Override
    public PageInfo<User> findByPage(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.findByPage();
        return new PageInfo<User>(userList);
    }

    /**
     * description: 门户登录
     * <br /><br />
     * create by mace on 2018/5/3 11:32.
     * @param username
     * @param password
     * @return: com.bonc.common.ResponseMessage<com.bonc.domain.User>
     */
    @Override
    public ResponseMessage<User> login(String username, String password){
        //1. 检查用户名是否存在
        if( userMapper.checkUserName(username) == 0 ){
            return ResponseMessage.createByErrorMessage("用户名不存在");
        }
        //2. MD5加密
        String md5Password = MD5Util.encode(password);
        //3. 登录
        User user = userMapper.login(username, md5Password);
        if( user == null ){
            return ResponseMessage.createByErrorMessage("密码错误");
        }
        //4.登录成功 --> 设置用户密码为"" 也可设置json不序列化密码字段
        user.setPassword(StringUtils.EMPTY);
        return ResponseMessage.createBySuccess("登录成功", user);
    }

    /**
     * description: 门户注册 角色为普通用户
     * <br /><br />
     * create by mace on 2018/5/7 10:35.
     * @param user
     * @return: com.bonc.common.ResponseMessage<java.lang.String>
     */
    @Override
    public ResponseMessage<String> register(User user) {
        //1.校验用户名或者邮箱是否已注册(存在)
        //1.1 校验用户名
        ResponseMessage<String> vaildResponse = checkVaild(user.getUsername(), Constant.USERNAME);
        if(!vaildResponse.isSuccess()){
            return vaildResponse;
        }
        //1.2 校验邮箱
        vaildResponse = checkVaild(user.getEmail(), Constant.EMAIL);
        if(!vaildResponse.isSuccess()){
            return vaildResponse;
        }
        //2.设置用户角色为普通用户
        user.setRole(Constant.Role.ROLE_CUSTOMER);
        //3.MD5密码加密
        user.setPassword(MD5Util.encode(user.getPassword()));
        //4.执行SQL
        if(userMapper.insert(user) == 0){
            return ResponseMessage.createByErrorMessage("注册失败");
        }
        return ResponseMessage.createBySuccessMessage("注册成功");
    }

    private ResponseMessage<String> checkVaild(String str, String type){

        //判断某字符串是否不为空且长度不为0且不由空白符(whitespace) 构成，等于 !isBlank(String str)
        if(StringUtils.isNotBlank(type) && StringUtils.isNotBlank(str)){
            //校验用户名
            if(Constant.USERNAME.equals(type)){
                if(userMapper.checkUserName(str) > 0){
                    return ResponseMessage.createByErrorMessage("用户名已存在");
                }
            }
            //校验邮箱
            if(Constant.EMAIL.equals(type)){
                if(userMapper.checkEmail(str) > 0){
                    return ResponseMessage.createByErrorMessage("邮箱已存在");
                }
            }
        }else{
            //非法参数
            return ResponseMessage.createByErrorResponseCode(ResponseCode.ILLEGAL_ARGUMENT);
        }
        return ResponseMessage.createBySuccessMessage("校验成功");
    }
}
