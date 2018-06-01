package com.mace.service_mysql;

import com.mace.common.BaseService;
import com.mace.common.ResponseMessage;
import com.mace.domain.User;

/**
 * description:
 * <br />
 * Created by mace on 15:53 2018/4/28.
 */
public interface IUserService extends BaseService<User, Integer> {

    ResponseMessage<User> login(String username, String password);
    ResponseMessage<String> register(User user);
}
