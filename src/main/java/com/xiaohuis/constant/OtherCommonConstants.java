package com.xiaohuis.constant;

/**
 * <p> 其它常用常量 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/12/2 22:36
 */
public class OtherCommonConstants {

    /**
     * 验证是否为作品的字面量
     */
    public static final String BLOG_TYPE = "works";

    /**
     * 验证是否为文件的字面量
     */
    public static final String FILE_TYPE_FILE = "file";

    /**
     * 验证是否为图片的字面量
     */
    public static final String FILE_TYPE_IMAGE  = "image";

    /**
     * 邮箱验证码的字面量
     */
    public static final String EMAIL_CODE_KEY  = "emailCode:";

    /**
     * 验证码的字面量
     */
    public static final String CHECK_CODE_KEY  = "checkCode:";

    /**
     * 角色验证的字面量
     */
    public static final String ROLE_KEY  = "admin";

    /**
     * 缓存links页面的key
     */
    public static final String LINKS_REDIS_KEY  = "links";

    /**
     * 缓存archives页面的key
     */
    public static final String ARCHIVES_REDIS_KEY  = "archives";



    /**
     * 手机号长度
     */
    public static final int MAX_PHONE_LENGTH  = 11;

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;

    /**
     * 文章内容最多可以输入的文字个数
     */
    public static final int MAX_CONTENT_COUNT = 200000;


    /**
     * 发送邮箱定义的字面量
     */

    public static final String MAIL_THEME_PWD  = "密码修改提醒";

    public static final String MAIL_THEME_EXCEPTION = "异常提醒";
    public static final String MAIL_CONTENT1 = "【xiaohui博客】验证码： ";

    public static final String MAIL_CONTENT2 = "  , 有效期5分钟，请尽快输入。";

    public static final String MAIL_CONTENT_EXCEPTION = "异常信息为 ： ";

}
