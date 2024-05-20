package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.entity.Tag;

import java.util.List;

public interface TagService {
    Tag createTag(Tag newTag);

    List<Tag> findTagsByName(List<Tag> tags);

    Tag updateTag(String tagName, Long tagId);

    Tag changeStatusTag(Long tagId);
}
