package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.entity.Publication_Tag;
import com.blogapp.sistemablog.entity.Tag;

import java.util.List;

public interface PublicationTagService {
    List<Publication_Tag> createPublicationTags(List<Tag> tagsFoundBD, Publication publication);
}
