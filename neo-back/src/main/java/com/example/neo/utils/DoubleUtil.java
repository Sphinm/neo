package com.example.neo.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * double工具类
 * @Author: xuhr
 * @Mail: 969858212@qq.com
 * @Date: 2020/11/5 10:27
 */
public class DoubleUtil {

    public static void main(String[] args) {
        System.out.println(formatDouble(44.4324));
        System.out.println(formatDouble(44.436666));
    }

    /**
     * 四舍五入保留两位小数
     * @param d
     * @return
     */
    public static Double formatDouble(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(2);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(nf.format(d));
    }
}
