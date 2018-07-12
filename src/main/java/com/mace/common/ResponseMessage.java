package com.mace.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.terran4j.commons.api2doc.annotations.ApiComment;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description: 统一处理返回前端的消息
 *
 * Created by mace on 17:49 2018/4/26.
 */
@JSONType(orders = {"requestId",
                    "serverTime",
                    "spendTime",
                    "resultCode",
                    "message",
                    "data",
                    "props"
})
public class ResponseMessage<T> implements Serializable {

    private static final long serialVersionUID = -6383969738771150638L;

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
     * 状态码
     */
    @JSONField(serialize = false)
    private int status;

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

    private ResponseMessage(int status){
        this.status = status;
    }
    private ResponseMessage(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ResponseMessage(int status,String msg,T data){
        this.status = status;
        this.message = msg;
        this.data = data;
    }

    private ResponseMessage(int status,String msg){
        this.status = status;
        this.message = msg;
    }

    private ResponseMessage(String msg, T data){
        this.status = ResponseCode.SUCCESS.getCode();
        this.resultCode = RestPackResponseCode.SUCCESS;
        this.message = msg;
        this.data = data;
    }

    private ResponseMessage(String resultCode, String message, HashMap<String, String> props) {
        this.status = -1;
        this.resultCode = resultCode;
        this.message = message;
        this.props = props;
    }

    //使之不在json序列化结果当中
    @JSONField(serialize = false)
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    @JSONField(serialize = false)
    public boolean success(){
        return RestPackResponseCode.SUCCESS.equals(this.message);
    }

    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public HashMap<String, String> getProps() {
        return props;
    }

    public void setProps(HashMap<String, String> props) {
        this.props = props;
    }


    public static <T> ResponseMessage<T> successByRestPack(String msg, T data){

        return new ResponseMessage<T>(msg, data);
    }


    public static <T> ResponseMessage<T> errorByRestPack(String resultCode, String message, HashMap<String, String> props){

        return new ResponseMessage<T>(resultCode, message, props);
    }



    /**
     * description: 请求成功,返回 ---> 0,success
     * <br /><br />       
     * create by mace on 2018/4/27 14:48.
     * @param   
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccess(){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg()
        );
    }

    /**
     * description: 请求成功,返回 ---> 0,自定义message
     * <br /><br />       
     * create by mace on 2018/4/27 14:48.
     * @param msg  
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccessMessage(String msg){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                msg
        );
    }

    /**
     * description: 请求成功，返回0,success,data
     * <br /><br />       
     * create by mace on 2018/4/27 14:44.
     * @param data  
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccess(T data){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                ResponseCode.SUCCESS.getMsg(),
                data
        );
    }

    /**
     * description: 请求成功，返回0,自定义message,data
     * <br /><br />
     * create by mace on 2018/4/27 14:50.
     * @param msg
     * @param data
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createBySuccess(String msg,T data){
        return new ResponseMessage<T>(
                ResponseCode.SUCCESS.getCode(),
                msg,
                data
        );
    }

    /**
     * description: 请求失败,返回-10，error
     * <br /><br />       
     * create by mace on 2018/4/27 14:52.
     * @param   
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByError(){
        return new ResponseMessage<T>(
                ResponseCode.ERROR.getCode(),
                ResponseCode.ERROR.getMsg()
        );
    }

    /**
     * description: 请求失败,返回-10，自定义message
     * <br /><br />       
     * create by mace on 2018/4/27 14:52.
     * @param errorMessage  
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorMessage(String errorMessage){
        return new ResponseMessage<T>(
                ResponseCode.ERROR.getCode(),
                errorMessage
        );
    }

    /**
     * description: 请求失败,返回自定义状态码，自定义message
     * <br /><br />       
     * create by mace on 2018/4/27 14:53.
     * @param errorCode
     * @param errorMessage
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ResponseMessage<T>(
                errorCode,
                errorMessage
        );
    }

    /**
     * description: 请求失败,根据ResponseCode返回
     * <br /><br />
     * create by mace on 2018/5/7 11:10.
     * @param responseCode
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorResponseCode(ResponseCode responseCode){
        return new ResponseMessage<T>(
                responseCode.getCode(),
                responseCode.getMsg()
        );
    }

    /**
     * description: 请求失败,根据ResponseCode返回, data
     * <br /><br />
     * create by mace on 2018/5/9 9:46.
     * @param responseCode
     * @param data
     * @return: com.bonc.common.ResponseMessage<T>
     */
    public static <T> ResponseMessage<T> createByErrorResponseCode(ResponseCode responseCode, T data){
        return new ResponseMessage<T>(
                responseCode.getCode(),
                responseCode.getMsg(),
                data
        );
    }

}
