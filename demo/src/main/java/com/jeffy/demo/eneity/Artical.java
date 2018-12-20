package com.jeffy.demo.eneity;

import java.util.Date;

/**
 * 实体类：文章（数据库表：artical)
 * author:Jeffy
 * Date:2018-11-25
 */
public class Artical {
    /**
    *id
     */
    private Integer id;
    /**
     *标题
     */
    private String title;
    /**
     *正文文档路径
     */
    private String content;
    /**
     *附件路径
     */
    private String attachmentPath;
    /**
     *类别id
     */
    private Integer typeId;
    /**
     *类别名
     */
    private String typeName;
    /**
     *作者id
     */
    private Integer authorId;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *更新时间
     */
    private Date updateTime;
    /**
     *点赞数
     */
    private Integer likeNum;
    /**
     *反对数
     */
    private Integer hateNum;
    /**
     *状态
     */
    private Integer status;
    /**
     *文章标签
     */
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getHateNum() {
        return hateNum;
    }

    public void setHateNum(Integer hateNum) {
        this.hateNum = hateNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Artical{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", attachmentPath='" + attachmentPath + '\'' +
                ", typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", authorId=" + authorId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", likeNum=" + likeNum +
                ", hateNum=" + hateNum +
                ", status=" + status +
                ", label='" + label + '\'' +
                '}';
    }
}
