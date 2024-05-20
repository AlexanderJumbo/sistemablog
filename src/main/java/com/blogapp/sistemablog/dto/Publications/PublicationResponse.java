package com.blogapp.sistemablog.dto.Publications;

import com.blogapp.sistemablog.dto.Category.CategoryResponse;
import com.blogapp.sistemablog.dto.ILike.ILikeResponse;
import com.blogapp.sistemablog.dto.Tag.PublicationTagResponse;
import com.blogapp.sistemablog.entity.Comment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicationResponse implements Serializable {

    private Long id;
    private String title;
    private String sutitle;
    private String content;
    private Date publishDate;

    private String author;

    private List<Comment> comments;

    private Integer likesCount;

    private List<ILikeResponse> likesInfo = new ArrayList<>();

    private PublicationTagResponse tags;

    private CategoryResponse category;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSutitle() {
        return sutitle;
    }

    public void setSutitle(String sutitle) {
        this.sutitle = sutitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public List<ILikeResponse> getLikesInfo() {
        return likesInfo;
    }

    public void setLikesInfo(List<ILikeResponse> likesInfo) {
        this.likesInfo = likesInfo;
    }

    public PublicationTagResponse getTags() {
        return tags;
    }

    public void setTags(PublicationTagResponse tags) {
        this.tags = tags;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }
}
