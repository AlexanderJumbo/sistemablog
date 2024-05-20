package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.ILike.ILikeResponse;

public interface ILikeService {

    ILikeResponse giveLikeToPublication(Long publicationId, Long userId);
}
