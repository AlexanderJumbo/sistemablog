package com.blogapp.sistemablog.repository;

import com.blogapp.sistemablog.entity.Publication;
import com.blogapp.sistemablog.entity.Publication_Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublicationTagRepository extends JpaRepository<Publication_Tag, Long> {
    @Query("SELECT i FROM Publication_Tag i WHERE i.publication.id = :publicationid")
    List<Publication_Tag> findByPublicationId(@Param("publicationid") Long publicationid);
}
