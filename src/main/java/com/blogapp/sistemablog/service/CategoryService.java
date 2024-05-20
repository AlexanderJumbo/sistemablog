package com.blogapp.sistemablog.service;

import com.blogapp.sistemablog.dto.Category.CategoryResponse;
import com.blogapp.sistemablog.dto.PublicationsPerCategorieResponse;
import com.blogapp.sistemablog.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Category findByName(String category);

    Category createCategory(String category);

    PublicationsPerCategorieResponse getPublicationsByCategory(int pageNumber, int numberRecordsPerPage, String orderRecordsByField, String orderRecordsByDirection, Long categoryId);

    CategoryResponse createCategoryFromController(String name);

    CategoryResponse updateCategory(String name, Long categoryId);

    CategoryResponse changeStatusCategory(Long categoryId);
}
