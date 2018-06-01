package com.mace.common;

/**
 * @Description: 响应状态码和响应描述
 *
 * Created by mace on 17:58 2018/4/26.
 */
public enum ResponseCode {

    SUCCESS(0,"success"),//成功

    NEED_LOGIN(-1,"need_login"),//需要登录
    ILLEGAL_ARGUMENT(-2,"illegal_argument"),//非法参数
    PERMISSION_DENIED(-3,"permission_denied"),//没有权限
    ERROR(-10,"error"),//错误
    EXCEPTION(-100,"exception");

//    REPEAT_REGISTER(-2, "该用户已注册"),
//    USER_NOT_EXIST(-3, "不存在该用户"),
//    PASSWORD_ERROR(-4, "密码错误"),
//    EMPTY_USERNAME(-5, "用户名为空"),
//    EMPTY_PASSWORD(-6, "密码为空");


    private final int code;
    private final String msg;

    private ResponseCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode(){ return this.code; }

    public String getMsg(){ return this.msg; }
}
