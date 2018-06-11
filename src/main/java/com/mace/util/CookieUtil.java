package com.mace.util;


import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

/**
 * description: cookie工具类
 * <br />
 * Created by mace on 17:20 2018/5/17.
 */
public class CookieUtil {

    //cookie key
    private String cookieName;
    //cookie 域名
    private String cookieDomain;
    //cookie 路径 默认当前应用
    private String cookiePath = "/";
    //cookie 有效期 -1 --> 关闭浏览器即失效 0 --> 立即删除  大于0 --> 有效时间 单位为秒
    private Integer cookieMaxAge;

    private boolean cookieSecure = false;
    private boolean cookieHttpOnly = false;
//    1 secure属性
//    当设置为true时，表示创建的 Cookie 会被以安全的形式向服务器传输，
//    也就是只能在 HTTPS连接中被浏览器传递到服务器端进行会话验证，
//    如果是 HTTP 连接则不会传递该信息，所以不会被窃取到Cookie 的具体内容。
//    2 HttpOnly属性
//    如果在Cookie中设置了"HttpOnly"属性，那么通过程序(JS脚本、Applet等)将无法读取到Cookie信息，这样能有效的防止XSS攻击。
//    对于以上两个属性，
//    首先，secure属性是防止信息在传递的过程中被监听捕获后信息泄漏，HttpOnly属性的目的是防止程序获取cookie后进行攻击。

    /**
     * description: 设置cookie
     * <br /><br />
     * create by mace on 2018/5/18 9:53.
     * @param response  HttpServletResponse
     * @param cookieName      cookie名字
     * @param cookieValue     cookie值
     * @param maxAge    cookie生命周期  以秒为单位
     * @return: void
     */
    public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge){

        CookieGenerator c = new CookieGenerator();
        c.setCookieName(cookieName);
        c.setCookieMaxAge(maxAge);
        //5afe3ea8742a481c5c4ba2b8_%E7%A6%BB%E7%BA%BF%E8%B4%AD%E7%89%A9%E8%BD%A6ID
        c.addCookie(response, UrlHelper.encode(cookieValue));
        //5afe3ea8742a481c5c4ba2b8_离线购物车ID
//        c.addCookie(response, cookieValue);
    }


    /**
     * description: 移除cookie
     * <br /><br />
     * create by mace on 2018/5/19 19:26.
     * @param response
     * @param cookieName
     * @return: void
     */
    public static void removeCookie(HttpServletResponse response, String cookieName){

        CookieGenerator c = new CookieGenerator();
        c.setCookieName(cookieName);

        c.removeCookie(response);
    }

    /**
     * description: 根据名字获取cookie  --> cookieMap中找
     * <br /><br />
     * create by mace on 2018/5/18 10:07.
     * @param request
     * @param cookieName
     * @return: javax.servlet.http.Cookie
     */
    public static Cookie getCookieByName(HttpServletRequest request, String cookieName){

        Map<String, Cookie> cookieMap = getCookieMap(request);

        if(Objects.nonNull(cookieMap)){

            if(cookieMap.containsKey(cookieName))
                return cookieMap.get(cookieName);
            else
                return null;
        }
        return null;
    }

    /**
     * description: 将cookie封装到Map里面
     * <br /><br />
     * create by mace on 2018/5/18 10:07.
     * @param request
     * @return: java.util.Map<java.lang.String,javax.servlet.http.Cookie>
     */
    public static Map<String,Cookie> getCookieMap(HttpServletRequest request){

        Map<String,Cookie> cookieMap = Maps.newHashMap();

        Cookie[] cookies = request.getCookies();

        if(Objects.nonNull(cookies) && cookies.length>0){

            for(Cookie cookie : cookies){

                cookieMap.put(cookie.getName(), cookie);
            }
            return cookieMap;
        }
        return null;
    }

    /**
     * description: 根据名字删除cookie
     * <br /><br />
     * create by mace on 2018/5/18 10:10.
     * @param request
     * @param response
     * @param cookieName
     * @return: boolean
     */
    public static boolean deleteCookie(HttpServletRequest request, HttpServletResponse response,String cookieName) {

        if (StringUtils.isNotBlank(cookieName)) {

            Cookie cookie = getCookie(request,cookieName);

            if(Objects.nonNull(cookie)){

                cookie.setMaxAge(0);//0，就立即删除
                cookie.setPath("/");//不要漏掉
                cookie.setDomain(request.getServerName());
                response.addCookie(cookie);
                return true;
            }
        }
        return false;
    }

    /**
     * description: 根据cookieName获取值  --> 直接遍历 cookies[]
     * <br /><br />
     * create by mace on 2018/5/18 10:18.
     * @param request
     * @param cookieName
     * @return: java.lang.String
     */
    public static String getValueByName(HttpServletRequest request,String cookieName){

        return getValueByCookie(getCookie(request,cookieName));
    }

    public static String getValueByCookie(Cookie cookie){

        if(Objects.isNull(cookie))
            return null;
        else
            return UrlHelper.decode(cookie.getValue());//5afe3ea8742a481c5c4ba2b8_离线购物车ID
    }

    public static Cookie getCookie(HttpServletRequest request,String cookieName){

        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        if ( Objects.nonNull(cookies) && cookies.length > 0) {

            for (int i = 0; i < cookies.length; i++) {

                cookie = cookies[i];
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * description: 通过 cookieName 检查是否存在
     * <br /><br />
     * create by mace on 2018/6/9 11:26.
     * @param request
     * @param cookieName
     * @return: boolean 存在 true 不存在 false
     */
    public static boolean isExist(HttpServletRequest request, String cookieName){

        if( getCookie(request, cookieName) == null )
            return false;
        else
            return true;
    }

    /**
     * description: 通过 cookieName 检查是否存在
     *
     *              存在 重置 cookie 生命周期 返回 true
     *              不存在返回 false
     * <br /><br />
     * create by mace on 2018/6/9 12:00.
     * @param request
     * @param cookieName
     * @param maxAge
     * @return: boolean
     */
    public static boolean resetCookieMaxAge(HttpServletRequest request, String cookieName, int maxAge){

        Cookie cookie = getCookie(request, cookieName);

        if( cookie != null ){
            //重置 cookie 生命周期
            cookie.setMaxAge(maxAge);
            return true;
        }
        return false;
    }

    /**
     * description: 重置 cookie 生命周期 并返回 cookieValue
     * <br /><br />
     * create by mace on 2018/6/11 11:20.
     * @param request
     * @param cookieName
     * @param maxAge
     * @return: java.lang.String
     */
    public static String resetCookie(HttpServletRequest request, String cookieName, int maxAge){

        Cookie cookie = getCookie(request, cookieName);

        if( cookie != null ){
            //重置 cookie 生命周期
            cookie.setMaxAge(maxAge);
            return getValueByCookie(cookie);
        }
        return null;
    }

}
