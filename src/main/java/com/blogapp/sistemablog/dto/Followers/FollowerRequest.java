package com.blogapp.sistemablog.dto.Followers;

import java.io.Serializable;

public class FollowerRequest implements Serializable {

    private Long idFollowedUser;
    private Long idUserFollower;

    public Long getIdFollowedUser() {
        return idFollowedUser;
    }

    public void setIdFollowedUser(Long idFollowedUser) {
        this.idFollowedUser = idFollowedUser;
    }

    public Long getIdUserFollower() {
        return idUserFollower;
    }

    public void setIdUserFollower(Long idUserFollower) {
        this.idUserFollower = idUserFollower;
    }
}
