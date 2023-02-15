package com.xiaohuis.api;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p> 七牛云 文件上传 api </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/11/22 9:57
 */
public class QiNiuCloudService {

    /**
     * 密钥凭证
     */
    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";
    /**
     * 仓库
     */
    private static final String BUCKET = "";

    /**
     * 七牛外网访问地址
     */
    public static final String QINIU_UPLOAD_SITE = "";

    /**
     * 上传文件到七牛公共方法
     * @param file          上传的文件
     * @param fileName      文件名
     * @return
     */
    public static String upload(MultipartFile file, String fileName) {
        // 构造一个带指定Zone对象的配置类
        // 华东 Region.region0(), Region.huadong()
        // 华北 Region.region1(), Region.huabei()
        // 华南 Region.region2(), Region.huanan()
        // 北美 Region.regionNa0(), Region.beimei()
        //东南亚 Region.regionAs0(), Region.xinjiapo()
        Configuration cfg = new Configuration(Region.region2());
        // 其它参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = null;
            response = uploadManager.put(file.getInputStream(), fileName, upToken, null, null);

            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return  putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            try {
                System.out.println(r.bodyString());
            } catch (QiniuException ex2) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
