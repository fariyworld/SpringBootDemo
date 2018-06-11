package com.mace.common;

/**
 * description:
 * <br />
 * Created by mace on 21:08 2018/5/27.
 */
public class Constant {

    public static final String DEFAULT_ENCODING_CHARTSET = "UTF-8";
    public static final String FTP_OTHER_IMAGES_PATH = "images/other/";

    public static final int DEFAULT_LIMIT_FILE_SIZE = 2;
    public static final String DEFAULT_MULTI_FILE_NAME = "multi_file";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    //当前用户常量 key
    public static final String CURRENT_USER = "current_user";

    public interface Redis{

        String KEY_USER_CART_PREFIX = "cartMap:";
    }

    public interface Cookie{

        String OFF_LINE_SHOPPING_CART_ID = "off_line_shopping_cart_id";
        int HALF_HOUR = 30 * 60;
        int ONE_WEEK = 7 * 24 * 3600;
        int ONE_MONTH = 30 * 24 * 3600;
    }

    //角色
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }

}
