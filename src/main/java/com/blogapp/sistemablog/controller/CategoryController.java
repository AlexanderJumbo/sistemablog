package com.blogapp.sistemablog.controller;

import com.blogapp.sistemablog.dto.Category.CategoryRequest;
import com.blogapp.sistemablog.dto.Category.CategoryResponse;
import com.blogapp.sistemablog.dto.PublicationsPerCategorieResponse;
import com.blogapp.sistemablog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{categoryId}")
    public PublicationsPerCategorieResponse getPublicationsByCategory(
            @RequestParam(value = "p", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "n", defaultValue = "10", required = false) int numberRecordsPerPage,
                @RequestParam(value = "f", defaultValue = "id", required = false) String orderRecordsByField,
            @RequestParam(value = "d", defaultValue = "asc", required = false) String orderRecordsByDirection,
            @PathVariable(name = "categoryId") Long categoryId)
    {
        return categoryService.getPublicationsByCategory(pageNumber, numberRecordsPerPage, orderRecordsByField, orderRecordsByDirection, categoryId);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategoryFromController(categoryRequest.getName()));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@RequestBody CategoryRequest categoryRequest, @PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.updateCategory(categoryRequest.getName(), categoryId));
    }

    @PutMapping("/{categoryId}/changeStatus")
    public ResponseEntity<CategoryResponse> changeStatusCategory(@PathVariable Long categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.changeStatusCategory(categoryId));
    }

}
