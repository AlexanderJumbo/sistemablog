package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.entity.Tag;
import com.blogapp.sistemablog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag newTag){
        Tag tag = tagService.createTag(newTag);
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }

    @PutMapping("/{tagId}")
    public ResponseEntity<Tag> updateTag(@RequestBody Tag tag, @PathVariable Long tagId){
        return ResponseEntity.status(HttpStatus.OK).body(tagService.updateTag(tag.getName(), tagId));
    }

    @PutMapping("/{tagId}/changeStatus")
    public ResponseEntity<Tag> changeStatusTag(@PathVariable Long tagId){
        return ResponseEntity.status(HttpStatus.OK).body(tagService.changeStatusTag(tagId));
    }


}
