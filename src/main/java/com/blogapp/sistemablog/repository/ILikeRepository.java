package com.blogapp.sistemablog.repository;

import com.blogapp.sistemablog.entity.ILike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILikeRepository extends JpaRepository<ILike, Long> {

    @Query("SELECT i FROM ILike i WHERE i.publication.id = :publicationId AND i.user.id = :userId")
    List<ILike> findLikesListByUser(@Param("publicationId") Long publicationId, @Param("userId") Long userId);

}
