package com.mace.controller.mongodb;

import com.mace.common.ResponseMessage;
import com.mace.mongodb.service.IMongoService;
import com.mace.service.mysql.IUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;

/**
 * description:
 * <br />
 * Created by mace on 20:25 2018/5/28.
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {


    private IMongoService iMongoService;
    private IUserService iUserService;


    @RequestMapping(value = "createCollection/{collectionName}")
    public ResponseMessage<String> createCollection(@PathVariable String collectionName){

        if(iMongoService.createCollection(collectionName))
            return ResponseMessage.createBySuccessMessage("创建" +collectionName+ " 成功");
        else
            return ResponseMessage.createBySuccessMessage("创建" +collectionName+ " 失败");
    }


    @RequestMapping(value = "importData/{collectionName}")
    public ResponseMessage<String> importData(@PathVariable String collectionName){

        if(iMongoService.insert(iUserService.findAll(), collectionName))
            return ResponseMessage.createBySuccessMessage("导入数据至" +collectionName+ " 成功");
        else
            return ResponseMessage.createBySuccessMessage("导入数据至" +collectionName+ " 失败");
    }
}
