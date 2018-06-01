package com.mace.MD5;

import java.security.MessageDigest;

/**
 * description: 加密解密工具类
 * <br />
 * Created by mace on 21:16 2018/4/29.
 */
public class MD5Util {

    private static String salt;
    static {
        salt = MD5Properties.salt;
    }


    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * description: MD5加密 编码
     * <br /><br />       
     * create by mace on 2018/5/2 17:01.
     * @param origin
     * @param charsetname
     * @return: java.lang.String
     */
    private static String encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString.toUpperCase();
    }

    /**
     * description:UTF8 MD5加密+salt
     * <br /><br />
     * create by mace on 2018/4/29 22:05.
     * @param encodeStr
     * @return: java.lang.String
     */
    public static String encode(String encodeStr) {
        encodeStr += salt;
        return encode(encodeStr, "utf-8");
    }

}
