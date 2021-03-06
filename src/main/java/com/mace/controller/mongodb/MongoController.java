package com.mace.controller.mongodb;

import com.mace.common.ResponseMessage;
import com.mace.domain.User;
import com.mace.mongodb.service.IMongoService;
import com.mace.service.mysql.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;
import java.util.List;

/**
 * description:
 * <br />
 * Created by mace on 20:25 2018/5/28.
 */
@RestController
@RequestMapping("/mongo")
@Slf4j
public class MongoController {

    @Autowired
    private IMongoService iMongoService;
    @Autowired
    private IUserService iUserService;


    @RequestMapping(value = "createCollection.do/{collectionName:.+}")
    public ResponseMessage<String> createCollection(@PathVariable("collectionName") String collectionName){

        log.info("要创建的表名：{}",collectionName);

        if(iMongoService.createCollection(collectionName))
            return ResponseMessage.createBySuccessMessage("创建 " +collectionName+ " 成功");
        else
            return ResponseMessage.createBySuccessMessage("创建 " +collectionName+ " 失败");
    }


    @RequestMapping(value = "importData.do/{collectionName}")
    public ResponseMessage<String> importData(@PathVariable("collectionName") String collectionName){

        log.info("要导入数据的表名：{}",collectionName);

        if(iMongoService.batchInsert(iUserService.findAll(), collectionName))
            return ResponseMessage.createBySuccessMessage("批量导入数据至 " +collectionName+ " 成功");
        else
            return ResponseMessage.createBySuccessMessage("批量导入数据至 " +collectionName+ " 失败");
    }


    @RequestMapping(value = "getAll.do")
    public ResponseMessage<List> getAll(String className, String collectionName){

        try {

            List list = iMongoService.getBeanList(Class.forName(className), collectionName);

            return ResponseMessage.createBySuccess(list);

        } catch (ClassNotFoundException e) {

            return ResponseMessage.createByErrorMessage("没有找到类："+className);
        }
    }
}
