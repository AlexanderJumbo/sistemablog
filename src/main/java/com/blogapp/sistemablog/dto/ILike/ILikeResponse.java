package com.blogapp.sistemablog.dto.ILike;

import java.io.Serializable;
import java.util.Date;

public class ILikeResponse implements Serializable {

    private Long Id;
    private String user;
    private String publication;
    private Date likeDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    @Override
    public String toString() {
        return "ILikeResponse{" +
                "Id=" + Id +
                ", user='" + user + '\'' +
                ", publication='" + publication + '\'' +
                ", likeDate=" + likeDate +
                '}';
    }
}
