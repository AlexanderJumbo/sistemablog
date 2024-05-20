package com.blogapp.sistemablog.repository;

import com.blogapp.sistemablog.dto.Publications.PublicationResponse;
import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    @Query("SELECT p FROM Publication p WHERE p.title like %:publicationTitle%")
    Page<Publication> getPublicationByTitle( Pageable pageable, @Param("publicationTitle") String publicationTitle);
}
