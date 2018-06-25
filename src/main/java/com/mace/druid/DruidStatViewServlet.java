package com.mace.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * description: Druid 监控控制器类
 * <br />
 * Created by mace on 18:24 2018/6/23.
 */
@WebServlet(
    urlPatterns = "/druid/*",
    initParams = {
        /** 白名单，如果不配置或value为空，则允许所有 */
        @WebInitParam(name = "allow", value = ""),
        /** 黑名单，与白名单存在相同IP时，优先于白名单 */
        @WebInitParam(name="deny",value=""),
        /** 用户名 */
        @WebInitParam(name="loginUsername", value="mace"),
        /** 密码 */
        @WebInitParam(name="loginPassword", value="liuye0425"),
        /** 禁用HTML页面上的“Reset All”功能   */
        @WebInitParam(name="resetEnable", value="false")
    }
)
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = -4275116750299781132L;
}
