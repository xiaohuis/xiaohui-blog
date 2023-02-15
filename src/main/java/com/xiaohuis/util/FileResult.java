package com.xiaohuis.util;

/**
 * <p> 文件结果返回 </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/11/21 15:14
 */
public class FileResult {
    private Integer success;
    private String message;
    private String url;

    public FileResult() {
    }

    public FileResult(Integer success, String message) {
        this.success = success;
        this.message = message;
    }

    public FileResult(Integer success, String message, String url) {
        this.success = success;
        this.message = message;
        this.url = url;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FileResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
