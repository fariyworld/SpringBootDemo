所用到的技术要点：
1.oracle
    1.1、oracle序列详解和创建自增主键
    -- 创建序列
    CREATE sequence seq_mace_dept_id
    	START WITH 3
    	INCREMENT BY 1
    	minvalue 3
    	maxvalue 9999999
    	nocycle;

    SELECT seq_mace_dept_id.CURRVAL from dual;

    -- 创建触发器
    create or replace trigger trigger_mace_dept_increase_id
      before insert on mace.DEPT   --mace.DEPT 是表名
      for each row
    declare
      nextid number;
    begin
      IF :new.ID IS NULL or :new.ID=0 THEN --ID是列名
        select seq_mace_dept_id.nextval    --seq_mace_dept_id正是刚才创建的序列
        into nextid
        from sys.dual;
        :new.ID:=nextid;
      end if;
    end trigger_mace_dept_increase_id;

    1.2、在oracle insert 后返回主键
    <selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER" >
        select seq_mace_dept_id.CURRVAL from dual
    </selectKey>

2.文件下载
//文件下载相关代码
@RequestMapping("/download")
public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
    String fileName = "aim_test.txt";// 设置文件名，根据业务需要替换成要下载的文件名
    if (fileName != null) {
        //设置文件路径
        String realPath = "D://aim//"
        File file = new File(realPath , fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    return null;
}

3.下载文件出现的异常 (void) 并不影响业务

2018-06-08 16:38:15.642  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : =========================请求 start======================================
2018-06-08 16:38:15.642  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : IP : 127.0.0.1
2018-06-08 16:38:15.642  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : URL : http://127.0.0.1:8088/ftp/downloadFile.do
2018-06-08 16:38:15.643  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : HTTP_METHOD : GET
2018-06-08 16:38:15.643  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : CLASS_METHOD : com.mace.controller.ftp.FTPController.downloadFile
2018-06-08 16:38:15.643  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : request参数===========================================start
2018-06-08 16:38:15.643  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : name: { url }, value: { ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg }
2018-06-08 16:38:15.643  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : request参数===========================================end
2018-06-08 16:38:15.643  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : URL_PATH参数: [ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg, com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper@58cc7325]
2018-06-08 16:38:15.870  INFO 12252 --- [nio-8088-exec-3] c.mace.ftp.service.impl.FTPServiceImpl   : 下载成功：ftp://192.168.88.132/images/other/5b18c851a623e42c90eb1cbc.jpg
2018-06-08 16:38:15.872  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : RESPONSE_TYPE : class com.mace.common.ResponseMessage
2018-06-08 16:38:15.873  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : RESPONSE : com.mace.common.ResponseMessage@7826bc56
2018-06-08 16:38:15.874  INFO 12252 --- [nio-8088-exec-3] com.mace.aop.WebLogAspect                : =========================请求 end======================================

2018-06-08 16:38:15.877  WARN 12252 --- [nio-8088-exec-3] .m.m.a.ExceptionHandlerExceptionResolver : Failed to invoke @ExceptionHandler method: public com.mace.common.ResponseMessage<com.mace.common.ExceptionInfo<java.lang.Exception>> com.mace.common.GlobalExceptionHandler.errorHandler(java.lang.Exception,javax.servlet.http.HttpServletRequest,org.springframework.web.method.HandlerMethod)

org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:288) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.handleReturnValue(RequestResponseBodyMethodProcessor.java:180) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite.handleReturnValue(HandlerMethodReturnValueHandlerComposite.java:82) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:119) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver.doResolveHandlerMethodException(ExceptionHandlerExceptionResolver.java:404) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver.doResolveException(AbstractHandlerMethodExceptionResolver.java:61) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:139) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.handler.HandlerExceptionResolverComposite.resolveException(HandlerExceptionResolverComposite.java:78) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.processHandlerException(DispatcherServlet.java:1255) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1062) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1008) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) [tomcat-embed-websocket-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) [druid-1.1.9.jar:1.1.9]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.session.web.http.SessionRepositoryFilter.doFilterInternal(SessionRepositoryFilter.java:147) [spring-session-core-2.0.3.RELEASE.jar:2.0.3.RELEASE]
	at org.springframework.session.web.http.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:81) [spring-session-core-2.0.3.RELEASE.jar:2.0.3.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1468) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) [na:1.8.0_66]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) [na:1.8.0_66]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at java.lang.Thread.run(Thread.java:745) [na:1.8.0_66]

2018-06-08 16:38:15.883  WARN 12252 --- [nio-8088-exec-3] .w.s.m.s.DefaultHandlerExceptionResolver : Handling of [org.springframework.web.HttpMediaTypeNotAcceptableException] resulted in exception

java.lang.IllegalStateException: Cannot call sendError() after the response has been committed
	at org.apache.catalina.connector.ResponseFacade.sendError(ResponseFacade.java:472) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at javax.servlet.http.HttpServletResponseWrapper.sendError(HttpServletResponseWrapper.java:129) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.session.web.http.OnCommittedResponseWrapper.sendError(OnCommittedResponseWrapper.java:106) ~[spring-session-core-2.0.3.RELEASE.jar:2.0.3.RELEASE]
	at javax.servlet.http.HttpServletResponseWrapper.sendError(HttpServletResponseWrapper.java:129) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at com.alibaba.druid.support.http.WebStatFilter$StatHttpServletResponseWrapper.sendError(WebStatFilter.java:342) ~[druid-1.1.9.jar:1.1.9]
	at org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver.handleHttpMediaTypeNotAcceptable(DefaultHandlerExceptionResolver.java:304) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver.doResolveException(DefaultHandlerExceptionResolver.java:180) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver.resolveException(AbstractHandlerExceptionResolver.java:139) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.handler.HandlerExceptionResolverComposite.resolveException(HandlerExceptionResolverComposite.java:78) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.processHandlerException(DispatcherServlet.java:1255) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1062) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1008) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851) [spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) [tomcat-embed-websocket-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) [druid-1.1.9.jar:1.1.9]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.session.web.http.SessionRepositoryFilter.doFilterInternal(SessionRepositoryFilter.java:147) [spring-session-core-2.0.3.RELEASE.jar:2.0.3.RELEASE]
	at org.springframework.session.web.http.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:81) [spring-session-core-2.0.3.RELEASE.jar:2.0.3.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) [spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1468) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) [na:1.8.0_66]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) [na:1.8.0_66]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at java.lang.Thread.run(Thread.java:745) [na:1.8.0_66]

2018-06-08 16:38:15.886 ERROR 12252 --- [nio-8088-exec-3] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Could not find acceptable representation] with root cause

org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation
	at org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor.writeWithMessageConverters(AbstractMessageConverterMethodProcessor.java:288) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor.handleReturnValue(RequestResponseBodyMethodProcessor.java:180) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.method.support.HandlerMethodReturnValueHandlerComposite.handleReturnValue(HandlerMethodReturnValueHandlerComposite.java:82) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:119) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:877) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:783) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:991) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:925) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:974) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:866) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:635) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:851) ~[spring-webmvc-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:742) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52) ~[tomcat-embed-websocket-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at com.alibaba.druid.support.http.WebStatFilter.doFilter(WebStatFilter.java:123) ~[druid-1.1.9.jar:1.1.9]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:109) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.session.web.http.SessionRepositoryFilter.doFilterInternal(SessionRepositoryFilter.java:147) ~[spring-session-core-2.0.3.RELEASE.jar:2.0.3.RELEASE]
	at org.springframework.session.web.http.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:81) ~[spring-session-core-2.0.3.RELEASE.jar:2.0.3.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:200) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107) ~[spring-web-5.0.6.RELEASE.jar:5.0.6.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198) ~[tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:96) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:496) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:81) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:342) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:803) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:790) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1468) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142) [na:1.8.0_66]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617) [na:1.8.0_66]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-8.5.31.jar:8.5.31]
	at java.lang.Thread.run(Thread.java:745) [na:1.8.0_66]

2018-06-08 16:38:16.014 ERROR 12252 --- [nio-8088-exec-3] w.s.e.ErrorMvcAutoConfiguration$SpelView : Cannot render error page for request [/ftp/downloadFile.do] and exception [Could not find acceptable representation] as the response has already been committed. As a result, the response may have the wrong status code.



4. maven 引用本地jar

    本地jar包放在 resources/lib下
    <dependency>
        <groupId>com.aliyun</groupId>
        <artifactId>aliyun-java-sdk-core</artifactId>
        <version>3.3.1</version>
        <scope>system</scope>
        <systemPath>${project.basedir}/src/main/resources/lib/aliyun-java-sdk-core-3.3.1.jar</systemPath>
    </dependency>
    编译打包时
    <resource>
        <directory>src/main/resources</directory>
        <includes>
            <include>**/*.*</include>
        </includes>
    </resource>

