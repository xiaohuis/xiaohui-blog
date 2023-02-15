package com.xiaohuis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 文件实体类
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */

public class Files implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 文件名
     */
    private String fileName;


    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件路径
     */
    private String fileKey;

    /**
     * 上传者
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态：0.逻辑删除；1.未删除
     */
    private int status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileKey='" + fileKey + '\'' +
                ", userId=" + userId +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
