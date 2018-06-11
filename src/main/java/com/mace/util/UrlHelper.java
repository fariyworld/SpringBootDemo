package com.mace.util;

import com.mace.common.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * description:
 * <br />
 * Created by mace on 18:25 2018/5/17.
 */
public class UrlHelper {

    private static final Logger logger = LoggerFactory.getLogger(UrlHelper.class);

    /**
     * description: UTF-8编码
     * <br /><br />
     * create by mace on 2018/5/17 18:33.
     * @param encodeStr
     * @return: java.lang.String
     */
    public static String encode(String encodeStr){

        try {
            return URLEncoder.encode(encodeStr, Constant.DEFAULT_ENCODING_CHARTSET);
        } catch (UnsupportedEncodingException e) {
            logger.error("The Character Encoding is not supported");
            logger.error("exception message: {}", e.getMessage());
            logger.error("exception stackTrace : {}", e.getStackTrace());
            return null;
        }
    }

    /**
     * description: UTF-8解码
     * <br /><br />
     * create by mace on 2018/5/17 18:33.
     * @param decodeStr
     * @return: java.lang.String
     */
    public static String decode(String decodeStr){

        try {
            return URLDecoder.decode(decodeStr, Constant.DEFAULT_ENCODING_CHARTSET);
        } catch (UnsupportedEncodingException e) {
            logger.error("The Character Encoding is not supported");
            logger.error("exception message: {}", e.getMessage());
            logger.error("exception stackTrace : {}", e.getStackTrace());
            return null;
        }
    }
}
