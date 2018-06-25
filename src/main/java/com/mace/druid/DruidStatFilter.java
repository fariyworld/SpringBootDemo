package com.mace.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * description: Druid 监控过滤器类
 * <br />
 * Created by mace on 18:34 2018/6/23.
 */
@WebFilter(
    filterName = "druidWebStatFilter", urlPatterns = "/*",
    initParams = {
        /** 忽略资源 */
        @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*,/static/*"),
        /** 监控当前COOKIE的用户 */
        @WebInitParam(name = "principalCookieName", value = "USER_COOKIE"),
        /** 监控当前SESSION的用户 */
        @WebInitParam(name = "principalSessionName", value = "USER_SESSION"),
        /** 监控单个url访问数据库情况 */
        @WebInitParam(name = "profileEnable", value = "true")
    }
)
public class DruidStatFilter extends WebStatFilter {
}
