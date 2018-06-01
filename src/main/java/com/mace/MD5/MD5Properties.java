package com.mace.MD5;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 22:43 2018/5/12.
 */
@Component
@ConfigurationProperties(prefix = "md5")
public class MD5Properties {

    public static String salt;

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
