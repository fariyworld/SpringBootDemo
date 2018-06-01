package com.mace.common;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * description:
 * <br />
 * Created by mace on 14:20 2018/5/8.
 */
public class ExceptionInfo<T extends Exception> {

    private String ip;
    private String url;
    private String class_method;
    private String ex_message;
    @JSONField(serialize = false)
    private T exception;//Exception
    private String stackTrace;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClass_method() {
        return class_method;
    }

    public void setClass_method(String class_method) {
        this.class_method = class_method;
    }

    public String getEx_message() {
        return ex_message;
    }

    public void setEx_message(String ex_message) {
        this.ex_message = ex_message;
    }

    public T getException() {
        return exception;
    }

    public void setException(T exception) {
        this.exception = exception;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
