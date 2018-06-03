package com.mace.aop;

import com.mace.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * description: web请求 aop统一日志
 * <br />
 * Created by mace on 20:47 2018/5/28.
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(* com.mace.controller..*.*(..))")
    public void ex() {
    }

    //前置通知
    @Before(value = "ex()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=========================请求 start======================================");
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
        }else{
            if(!Arrays.toString(joinPoint.getArgs()).equals("[{}]")){
                log.info("URL_PATH参数: "+ Arrays.toString(joinPoint.getArgs()));
            }
        }

    }

    //异常通知
    @AfterThrowing(throwing = "e", pointcut = "ex()")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e){

        log.error("异常信息: {}",e.getMessage());
        log.error("异常堆栈: {}",e.getStackTrace());
        log.info("=========================请求 异常 end======================================");
    }

    //返回通知
    @AfterReturning(returning = "result",pointcut = "ex()")
    public void doAfterReturning(Object result){

        // 处理完请求，返回内容
        log.info("RESPONSE_TYPE : " + result.getClass());
        log.info("RESPONSE : " + result);
        log.info("=========================请求 end======================================");
        System.out.println("\t");
    }

    public static String getClientIp(HttpServletRequest request){

        String remoteAddr = StringUtils.EMPTY;;

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
