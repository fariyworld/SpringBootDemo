package com.mace.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.terran4j.commons.api2doc.annotations.ApiComment;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * description: (AOP) 统一处理返回前端的消息
 * <br />
 * Created by mace on 18:02 2018/6/26.
 */
@JSONType(orders = {"requestId",
        "serverTime",
        "spendTime",
        "resultCode",
        "message",
        "data",
        "props"
})
public class RestPackResponse<T> implements Serializable {

    private static final long serialVersionUID = -8209507415226273977L;

    /*
     * 服务端生成的请求唯一ID号，当这个请求有问题时，可以拿着这个 ID 号，在海量日志快速查询到此请求的日志信息，以方便排查问题
     */
    private String requestId;

    /*
     * 服务器时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date serverTime;

    /*
     * 本次请求在服务器端处理所消耗的时间，这里显示出来以方便诊断慢请求相关问题
     */
    private long spendTime;

    /*
     * 结果码 与 ResponseCode 一一对应
     *
     * "success" 表示成功，其它表示一个错误的错误码
     */
    private String resultCode;

    /*
     * 信息描述
     */
    private String message;

    /*
     * 实际的业务数据，内容由每个 API 的业务逻辑决定
     */
    private T data;

    /**
     * 错误上下文相关属性
     */
    @ApiComment(value = "错误上下文相关属性", sample = "key:属性，value:属性值")
    private HashMap<String, String> props;

    //使之不在json序列化结果当中
    @JSONField(serialize = false)
    public boolean isSuccess(){
        return RestPackResponseCode.SUCCESS.equals(this.message);
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Date getServerTime() {
        return serverTime;
    }

    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    public long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(long spendTime) {
        this.spendTime = spendTime;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HashMap<String, String> getProps() {
        return props;
    }

    public void setProps(HashMap<String, String> props) {
        this.props = props;
    }

    private RestPackResponse(String msg, T data){
        this.resultCode = RestPackResponseCode.SUCCESS;
        this.message = msg;
        this.data = data;
    }

    private RestPackResponse(Exception ex, T data){
        this.resultCode = RestPackResponseCode.EXCEPTION;
        this.message = ex.getClass().toString();
        this.data = data;
    }

    private RestPackResponse(String resultCode, String message, HashMap<String, String> props) {
        this.resultCode = resultCode;
        this.message = message;
        this.props = props;
    }

    public static <T> RestPackResponse<T> createBySuccess(String message, T data){

        return new RestPackResponse<T>(message, data);
    }

    public static <T> RestPackResponse<T> createByException(Exception ex, T data){

        return new RestPackResponse<T>(ex, data);
    }

    public static <T> RestPackResponse<T> createByError(String resultCode, String message, HashMap<String, String> props){

        return new RestPackResponse<T>(resultCode, message, props);
    }
}
