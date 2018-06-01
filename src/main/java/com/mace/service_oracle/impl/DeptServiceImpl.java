package com.mace.service_oracle.impl;

import com.mace.common.ResponseMessage;
import com.mace.entity.Dept;
import com.mace.mapper_oracle.DeptMapper;
import com.mace.service_oracle.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:
 * <br />
 * Created by mace on 11:30 2018/5/30.
 */
@Service("iDeptService")
public class DeptServiceImpl implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;


    public ResponseMessage<String> register(Dept dept){

        if(deptMapper.insert(dept) == 0){
            return ResponseMessage.createByErrorMessage("注册部门信息失败");
        }
        return ResponseMessage.createBySuccessMessage("注册部门信息成功");
    }
}
