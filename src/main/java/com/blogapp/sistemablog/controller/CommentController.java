package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.dto.Comments.CommentRequest;
import com.blogapp.sistemablog.dto.Comments.CommentResponse;
import com.blogapp.sistemablog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest){
        CommentResponse commentResponse = commentService.createComment(commentRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
    }

    @GetMapping("/publication/{publicationId}")
    public ResponseEntity<Page<CommentResponse>> getCommentsByPublication(@PathVariable Long publicationId, Pageable pageable){
        Page<CommentResponse> comments = commentService.getCommentsByPublication(pageable, publicationId);
        if(comments.hasContent()){
            return ResponseEntity.ok(comments);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/publication/{publicationId}/comment/{commentId}")
    public ResponseEntity<String> deleteCommentFromPublication(@PathVariable(value = "publicationId") Long publicationId, @PathVariable(value = "commentId") Long commentId){
        commentService.deleteCommentFromPublication(publicationId, commentId);
        return ResponseEntity.ok("Comentario borrado con éxito");
    }

}
