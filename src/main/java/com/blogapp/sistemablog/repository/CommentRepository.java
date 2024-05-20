package com.blogapp.sistemablog.repository;

import com.blogapp.sistemablog.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPublicationId(Pageable pageable, Long publicationId);
}
