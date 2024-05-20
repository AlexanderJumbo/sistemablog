package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Comments.CommentRequest;
import com.blogapp.sistemablog.dto.Comments.CommentResponse;
import com.blogapp.sistemablog.entity.Comment;
import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.entity.security.User;
import com.blogapp.sistemablog.exception.ObjectNotFoundException;
import com.blogapp.sistemablog.repository.CommentRepository;
import com.blogapp.sistemablog.repository.PublicationRepository;
import com.blogapp.sistemablog.repository.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {

        Publication publication = publicationRepository.findById(commentRequest.getPublication())
                .orElseThrow(() -> new ObjectNotFoundException("Publication not found. Publication Id: " + commentRequest.getPublication()));

        User user = userRepository.findByUsername(commentRequest.getUser())
                .orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + commentRequest.getUser()));

        Comment newComment = new Comment();
        newComment.setContent(commentRequest.getContent());
        newComment.setUser(user.getUsername());
        newComment.setPublication(publication);

        commentRepository.save(newComment);
        return mapDTO(newComment);
    }

    @Override
    public Page<CommentResponse> getCommentsByPublication(Pageable pageable, Long publicationId) {

        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ObjectNotFoundException("Publication not found. Publication Id: " + publicationId));

        return commentRepository.findByPublicationId(pageable, publicationId).map(this::mapDTO);
    }

    @Override
    public void deleteCommentFromPublication(Long publicationId, Long commentId) {

        Publication publication = publicationRepository.findById(publicationId)
                        .orElseThrow(() -> new ObjectNotFoundException("Publication not found. Publication Id: " + publicationId));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ObjectNotFoundException("Comment not found. Comment Id: " + commentId));

        if(!comment.getPublication().getId().equals(publication.getId())){
            throw new ObjectNotFoundException("The comment does not belong to the publication");
        }

        commentRepository.delete(comment);
    }

    private CommentResponse mapDTO(Comment newComment) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(newComment.getId());
        commentResponse.setContent(newComment.getContent());
        commentResponse.setUser(newComment.getUser());
        commentResponse.setPublication(newComment.getPublication().getTitle());
        return commentResponse;
    }
}
