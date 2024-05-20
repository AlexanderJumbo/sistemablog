package com.blogapp.sistemablog.dto.Comments;

import java.io.Serializable;

public class CommentRequest implements Serializable {

    private String content;
    private String user;
    private long publication;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getPublication() {
        return publication;
    }

    public void setPublication(long publication) {
        this.publication = publication;
    }
}
