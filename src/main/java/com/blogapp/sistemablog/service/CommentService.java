package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Comments.CommentRequest;
import com.blogapp.sistemablog.dto.Comments.CommentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentResponse createComment(CommentRequest commentRequest);

    Page<CommentResponse> getCommentsByPublication(Pageable pageable, Long publicationId);

    void deleteCommentFromPublication(Long publicationId, Long commentId);
}
