package com.mace.test;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description: 测试Encyptor加密
 * <br />
 * Created by mace on 19:55 2018/5/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncyptorTest {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void test(){

        String encrypt = stringEncryptor.encrypt("liuye0425");
        //Qml0BS/5j8LD9dyKa+Eo/Y4DhmGofWLQ
        System.out.println("加密后的密码: "+encrypt);
    }
}
