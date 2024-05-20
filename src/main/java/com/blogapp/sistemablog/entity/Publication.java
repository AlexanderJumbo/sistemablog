package com.blogapp.sistemablog.entity;

import com.blogapp.sistemablog.dto.ILike.ILikeResponse;
import com.blogapp.sistemablog.entity.security.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "publications")
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String sutitle;
    private String content;
    private Date publishDate;

    @JsonBackReference
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<ILike> likesInfo = new ArrayList<>();

    @Transient
    private List<ILikeResponse> likesInfoFormat;//Para presentar por pantalla una respuesta formateada o

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Publication_Tag> tagList = new ArrayList<>();

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;


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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<ILike> getLikes() {
        return likesInfo;
    }

    public void setLikes(List<ILike> likesInfo) {
        this.likesInfo = likesInfo;
    }

    public List<ILike> getLikesInfo() {
        return likesInfo;
    }

    public void setLikesInfo(List<ILike> likesInfo) {
        this.likesInfo = likesInfo;
    }

    public List<Publication_Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Publication_Tag> tagList) {
        this.tagList = tagList;
    }

    public List<ILikeResponse> getAllLikesToPublications (){
        likesInfoFormat = new ArrayList<>();

        for(ILike likes : likesInfo){
            ILikeResponse items = new ILikeResponse();
            items.setId(likes.getId());
            items.setUser(likes.getUser().getUsername());
            items.setPublication(likes.getPublication().getTitle());
            items.setLikeDate(likes.getLikeDate());
            System.out.println(items);
            likesInfoFormat.add(items);
        }

        return likesInfoFormat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Publication{" +
                ", category=" + category +
                '}';
    }
}
