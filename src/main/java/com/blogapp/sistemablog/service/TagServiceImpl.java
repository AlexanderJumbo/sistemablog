package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.entity.Tag;
import com.blogapp.sistemablog.exception.ObjectNotFoundException;
import com.blogapp.sistemablog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag createTag(Tag newTag) {

        Tag findTag = tagRepository.findByName(newTag.getName());

        if(findTag != null){
            throw new ObjectNotFoundException("Tag exist in BD");
        }

        return tagRepository.save(newTag);
    }

    @Override
    public List<Tag> findTagsByName(List<Tag> tags) {
                                                        // tag -> tag.getName()
        return tagRepository.findByNameIn(tags.stream().map(Tag::getName).collect(Collectors.toList()));
    }

    @Override
    public Tag updateTag(String tagName, Long tagId) {

        Tag findTag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ObjectNotFoundException("Tag not found. Tag Id: " + tagId));

        findTag.setName(tagName);
        return tagRepository.save(findTag);
    }

    @Override
    public Tag changeStatusTag(Long tagId) {

        Tag findTag = tagRepository.findById(tagId)
                .orElseThrow(() -> new ObjectNotFoundException("Tag not found. Tag Id: " + tagId));

        findTag.setStatus(!findTag.isStatus());

        return tagRepository.save(findTag);
    }
}
