package com.mace.util;

import java.lang.Double;
import java.math.BigDecimal;

/**
 * description: 商业货币运算 损失精度 使用 BigDecimal的String构造器解决
 * <br />
 * Created by mace on 11:04 2018/5/13.
 */
public class BigDecimalUtil {

    private BigDecimalUtil(){

    }

    /**
     * description: 加法
     * <br /><br />
     * create by mace on 2018/5/17 11:57.
     * @param v1
     * @param v2
     * @return: java.lang.Double
     */
    public static Double add(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }


    /**
     * description: 减法
     * <br /><br />
     * create by mace on 2018/5/17 11:57.
     * @param v1  被减数
     * @param v2  减数
     * @return: java.lang.Double
     */
    public static Double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }


    /**
     * description: 乘法
     * <br /><br />
     * create by mace on 2018/5/17 11:57.
     * @param v1
     * @param v2
     * @return: java.lang.Double
     */
    public static Double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }


    /**
     * description: 除法
     * <br /><br />
     * create by mace on 2018/5/17 11:57.
     * @param v1 除数
     * @param v2 被除数
     * @return: java.lang.Double
     */
    public static Double div(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        //除不尽的情况 四舍五入,保留2位小数
        return b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
