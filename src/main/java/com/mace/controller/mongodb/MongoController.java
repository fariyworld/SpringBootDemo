package com.mace.controller.mongodb;

import com.mace.mongodb.service.IMongoService;
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
}
