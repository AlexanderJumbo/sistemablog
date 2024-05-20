package com.blogapp.sistemablog.dto.Tag;

import com.blogapp.sistemablog.entity.Publication_Tag;
import com.blogapp.sistemablog.entity.Tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PublicationTagResponse implements Serializable {

    private List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
