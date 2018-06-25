package com.mace.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * description: 校验参数的合法性
 * <br />
 * Created by mace on 12:18 2018/6/7.
 */
public class CheckParamUtil {

    /**
     * description: 校验字符串不为 null 且 不为空白串
     * <br /><br />
     * create by mace on 2018/6/7 12:25.
     * @param str
     * @return: boolean  true 合法  false 非法
     */
    public static boolean isEffective(String str){

        if( str != null && StringUtils.isNotBlank(str))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {

        System.out.println(isEffective("a.txt"));

        //设置文件路径
        String realPath = "D:\\WebLogs\\SpringBootDemo\\download\\images\\other";
        File file = new File(realPath , "hello.txt");

        System.out.println(file.exists());

        String url = "ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg";

        // 192.168.88.132/images/other/
        String tempPath = url.substring(url.indexOf("/")+2, url.lastIndexOf("/")+1);

        System.out.println(tempPath.substring(tempPath.indexOf("/")+1));

        System.out.println(url.substring(url.lastIndexOf("/")+1));


        int[] arr = {1,2,3,5,7,9};

        for (int i : arr) {
            System.out.println(i);
            if(3==i){
                break;
            }
        }

        System.out.println(StringHelper.getUUIDString().length());
    }
}
