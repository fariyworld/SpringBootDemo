package com.mace.ftp.config;

import org.apache.commons.net.ftp.FTP;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * description:
 * <br />
 * Created by mace on 20:40 2018/5/27.
 */
@Component
@ConfigurationProperties(prefix = "ftp")
public class FTPProperties {

    public static String host;
    public static int ip = FTP.DEFAULT_PORT;
    public static String username;
    public static String password;
    public static String prefix;
    public static String remotePath;
    public static int bufferSize;


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrefix() { return prefix; }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getRemotePath() { return remotePath; }

    public void setRemotePath(String remotePath) { this.remotePath = remotePath; }

    public int getBufferSize() { return bufferSize; }

    public void setBufferSize(int bufferSize) { this.bufferSize = bufferSize; }
}
