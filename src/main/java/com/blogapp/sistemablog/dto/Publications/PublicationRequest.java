package com.blogapp.sistemablog.dto.Publications;

import com.blogapp.sistemablog.entity.Comment;
import com.blogapp.sistemablog.entity.Publication_Tag;
import com.blogapp.sistemablog.entity.Tag;

import java.io.Serializable;
import java.util.List;

public class PublicationRequest implements Serializable {

    //private Long id;
    private String title;
    private String sutitle;
    private String content;
    private String author;
    private List<Comment> comments;

    private List<Tag> tags;

    private String category;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
