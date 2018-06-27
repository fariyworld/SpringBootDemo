package com.mace.aop;

import com.mace.common.Constant;
import com.mace.common.ExceptionInfo;
import com.mace.common.RestPackResponse;
import com.mace.util.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * description: web请求 aop统一日志
 * <br />
 * Created by mace on 20:47 2018/5/28.
 */
@Order(0)//index的值越小，所对应的优先级就越高
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    private long START_TIME;
    private long END_TIME;
    private String requestId;
    private ExceptionInfo<Exception> exceptionInfo = null;

    @Pointcut("execution(* com.mace.controller..*.*(..))")
    public void ex() {
    }

    //前置通知
    @Before(value = "ex()")
    public void doBefore(JoinPoint joinPoint){

        requestId = StringHelper.getUUIDString();
        START_TIME = System.currentTimeMillis();

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=========================请求 start======================================");
        log.info("requestId : " + requestId);
        log.info("IP : " + getClientIp(request));
        log.info("URL : "+ getRequestUrl(request));
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName());

        Map<String, String[]> parameterMap = request.getParameterMap();
        if ( parameterMap.size() > 0 ){
            log.info("request参数===========================================start");
            Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for(Map.Entry<String, String[]> entry:entrySet){
                String key = entry.getKey();
                if(key.equals("password")){
                    log.info("name: { "+key+" }, value: {  }");
                }else{
                    log.info("name: { "+key+" }, value: { "+request.getParameter(key)+" }");
                }
            }
            log.info("request参数===========================================end");
        }

        if(!Arrays.toString(joinPoint.getArgs()).equals("[{}]")){
            log.info("URL_PATH参数: {}",Arrays.toString(joinPoint.getArgs()));
        }
    }

    //后置通知
    @After("ex()")
    public void doAfter(JoinPoint joinPoint){
    }

    //异常通知
    @AfterThrowing(throwing = "ex", pointcut = "ex()")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex){

        END_TIME = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();

        request.setAttribute("requestId", requestId);
        request.setAttribute("START_TIME", START_TIME);
        request.setAttribute("END_TIME", END_TIME);

        log.error("异常信息: {}",ex.getMessage());
        log.error("异常堆栈: {}",ex.getStackTrace());
        log.info("=========================请求 异常 end======================================");
    }

    //返回通知
    @AfterReturning(returning = "result",pointcut = "ex()")
    public void doAfterReturning(Object result){
        // 处理完请求，返回内容
        END_TIME = System.currentTimeMillis();

        if(result instanceof RestPackResponse){

            RestPackResponse response = (RestPackResponse) result;

            response.setRequestId(requestId);
            response.setServerTime(new Date(START_TIME));
            response.setSpendTime((END_TIME - START_TIME));
        }

        log.info("RESPONSE_TYPE : " + result.getClass());
        log.info("RESPONSE : " + result);
        log.info("=========================请求 end======================================");
        System.out.println("\t");
    }

    //环绕通知
    //@Around(value = "ex()")
    public Object doAround(ProceedingJoinPoint pjd){
        Object result =null;

        try {
            // 环绕前

            // 前置通知

            // 执行被代理的方法
            result = pjd.proceed();

            // 环绕后

        } catch (Throwable throwable) {
            // 异常通知 (如果有返回通知，则在返回通知之后执行)
            System.out.println(throwable.getMessage());
        }

        //后置通知

        // 返回通知

        return result;
    }

    public static String getClientIp(HttpServletRequest request){

        String remoteAddr = StringUtils.EMPTY;

        if(request!=null){

            remoteAddr = request.getHeader("X-FORWARDED-FOR");

            if(remoteAddr==null || "".equals(remoteAddr)){

                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }


    public static String getRequestUrl(HttpServletRequest request){

        String url = StringUtils.EMPTY;

        try {
            url =  URLDecoder.decode(request.getRequestURL().toString(), Constant.DEFAULT_ENCODING_CHARTSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}
