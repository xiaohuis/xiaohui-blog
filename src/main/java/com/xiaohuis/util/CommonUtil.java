package com.xiaohuis.util;

import com.xiaohuis.constant.SystemConstant;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> 常用工具类 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/2 20:49
 */
public class CommonUtil {

    /**
     * 判断文件是否为图片
     * @param file 文件
     * @return
     */
    public static boolean isImage(InputStream file){
        try {
            Image img = ImageIO.read(file);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 设置记住密码cookie
     * @param response
     * @param userId
     */
    public static void setCookie(HttpServletResponse response, Long userId) {
        try {
            String val = EncryptionUtil.enAes(userId.toString(), SystemConstant.AES_SALT);
            boolean isSSL = false;
            Cookie cookie = new Cookie(SystemConstant.USER_LOGIN_COOKIE, val);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setSecure(isSSL);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取cookie中的用户ID
     * @param request
     * @return
     */
    public static Long getCookieUid(HttpServletRequest request){
        if (null != request) {
            Cookie cookie = cookieRaw(SystemConstant.USER_LOGIN_COOKIE,request);
            if (cookie != null && cookie.getValue() != null) {
                try {
                    String userId = EncryptionUtil.deAes(cookie.getValue(), SystemConstant.AES_SALT);
                    return StringUtils.isNotBlank(userId) ? Long.parseLong(userId) : null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    public static String getCookieToken(HttpServletRequest request){
        if (null != request) {
            Cookie cookie = cookieRaw(SystemConstant.USER_LOGIN_COOKIE,request);
            if (cookie != null && cookie.getValue() != null) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 从cookies中获取指定cookie
     * @param name          名称
     * @param request       请求
     * @return  cookie
     */
    private static Cookie cookieRaw(String name, HttpServletRequest request) {
        Cookie[] servletCookies = request.getCookies();
        if (servletCookies == null) {
            return null;
        }
        for (Cookie c: servletCookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * 按照输入的格式获取时间
     * @param date  时间
     * @param dateFormat 格式
     * @return
     */
    public static String dateFormat(Date date, String dateFormat) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            return format.format(date);
        }
        return "";
    }


    /**
     * 输出带有路径的文件名
     * @param name
     * @return
     */
    public static String getFileKey(String name) {
        String prefix = "/upload/" + dateFormat(new Date(), "yyyy/MM");
        name = StringUtils.trimToNull(name);
        if (name == null) {
            return prefix + "/" +UUID.UU32() + "." + null;
        } else {
            name = name.replace('\\','/');
            name = name.substring(name.lastIndexOf("/") + 1);
            int index = name.lastIndexOf(".");
            String ext = null;
            if (index > 0) {
                ext = StringUtils.trimToNull(name.substring(index + 1));
            }
            return prefix + "/" + UUID.UU32() + "." + (ext == null ? null : (ext));
        }
    }


    /**
     * 匹配邮箱正则
     */
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * 判断是否是邮箱
     * @param emailStr
     * @return
     */
    public static boolean isEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

}
