package com.blogapp.sistemablog.dto.ILike;

import java.io.Serializable;

public class ILikeRequest implements Serializable {

    private Long publicationId;
    private Long userId;

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
