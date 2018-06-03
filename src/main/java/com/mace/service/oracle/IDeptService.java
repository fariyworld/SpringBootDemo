package com.mace.service.oracle;

import com.mace.common.ResponseMessage;
import com.mace.entity.Dept;

/**
 * description:
 * <br />
 * Created by mace on 11:30 2018/5/30.
 */
public interface IDeptService {

    ResponseMessage<String> register(Dept dept);
}
