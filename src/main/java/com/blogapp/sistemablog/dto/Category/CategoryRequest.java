package com.blogapp.sistemablog.dto.Category;

import java.io.Serializable;

public class CategoryRequest implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
