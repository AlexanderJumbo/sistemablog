package com.blogapp.sistemablog.entity;

import com.blogapp.sistemablog.entity.security.User;
import jakarta.persistence.*;

@Entity
@Table(name = "followers")
public class Follower {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User following;//usuario seguido

    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;//seguidor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
