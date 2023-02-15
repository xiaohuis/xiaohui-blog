package com.xiaohuis.util;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.Random;


/**
 * <p> 前端常用公共函数工具类 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/2 20:49
 */
@Component
public class FrontEndCommonsUtil {

    private static final Random random = new Random();


    /**
     * 随机数
     * @param min
     * @param max
     * @return
     */
    public static int rand(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 定义颜色样式数组
     */
    private static final String[] COLORS = {"default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink"};

    /**
     * 随机样式
     * @return
     */
    public static String rand_color() {
        int r = rand(0, COLORS.length - 1);
        return COLORS[r];
    }

    static Random r = new Random();

    /**
     * 根据一个范围，生成一个随机的整数
     * @param min   最小值（包括）
     * @param max   最大值（包括）
     * @return  随机数
     */
    public static int random(int min, int max) {
        return r.nextInt(max - min + 1) + min;
    }

    /**
     * 获取随机数数
     * @param max
     * @param str
     * @return
     */
    public static String random(int max, String str) {
        return random(1,max) + str;
    }

    public static String random(Long seed, int max, String str) {
        if (seed == null) {
            return random(max, str);
        }
        Random random = new Random(seed);
        return random.nextInt(max) +str;
    }

    /**
     * 生成指定范围的随机整数
     * @param member
     * @return
     */
    public static String randomInt(int member,String suf) {
        Random random = new Random();
        return random.nextInt(member) + member + suf;
    }


    /**
     * 字符串截取
     * @param str   截取的字符串
     * @param len   截取的长度
     * @return      截取之后字符串
     */
    public static String subStr(String str, Integer len) {
        if (null == len) {
            len = 100;
        }
        String tempStr = null;
        if (str.length() > len) {
            tempStr = str.substring(0,len);
            return tempStr + "...";
        }
        return str;
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0, len);
        }
        return str;

    }


    /**
     * 判断分页中是否有数据
     * @param pageInfo
     * @return
     */
    public static boolean is_empty(PageInfo pageInfo) {
        return pageInfo == null || (pageInfo.getList() == null) || (pageInfo.getList().size() == 0);
    }



}
