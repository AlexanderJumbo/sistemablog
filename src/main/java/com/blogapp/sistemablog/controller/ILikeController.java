package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.dto.ILike.ILikeRequest;
import com.blogapp.sistemablog.dto.ILike.ILikeResponse;
import com.blogapp.sistemablog.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class ILikeController {

    @Autowired
    private ILikeService iLikeService;
    @PostMapping
    public ResponseEntity<ILikeResponse> giveLikeToPublication(@RequestBody ILikeRequest iLikeRequest){

        ILikeResponse newLike = iLikeService.giveLikeToPublication(iLikeRequest.getPublicationId(), iLikeRequest.getUserId());

        return ResponseEntity.status(HttpStatus.OK).body(newLike);
    }

}
