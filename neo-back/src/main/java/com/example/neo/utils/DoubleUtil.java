package com.example.neo.utils;

/**
 * double工具类
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/11/5 10:27
 */
public class DoubleUtil {

    public static void main(String[] args) {
        System.out.println(formatDouble(44.4));
        System.out.println(formatDouble(44.436666));
    }

    /**
     * 四舍五入保留两位小数
     * @param d
     * @return
     */
    public static Double formatDouble(double d) {
        return (double) Math.round(d * 100) / 100;
    }
}
