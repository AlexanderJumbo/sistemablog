package com.blogapp.sistemablog.repository;

import com.blogapp.sistemablog.entity.Category;
import com.blogapp.sistemablog.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String category);

    @Query("SELECT p FROM Publication p WHERE p.category.id = :categoryId ORDER BY " +
            "CASE WHEN :orderRecordsByDirection = 'ASC' AND :orderRecordsByField = 'title' THEN p.title END ASC, " +
            "CASE WHEN :orderRecordsByDirection = 'DESC' AND :orderRecordsByField = 'title' THEN p.title END DESC, " +
            "CASE WHEN :orderRecordsByDirection = 'ASC' AND :orderRecordsByField = 'publishDate' THEN p.publishDate END ASC, " +
            "CASE WHEN :orderRecordsByDirection = 'DESC' AND :orderRecordsByField = 'publishDate' THEN p.publishDate END DESC")
    Page<Publication> findPublicationsByCategoryId(Pageable pageable,
                                                   @Param("categoryId") Long categoryId,
                                                   @Param("orderRecordsByField") String orderRecordsByField,
                                                   @Param("orderRecordsByDirection") String orderRecordsByDirection
                                                   );
}
