package com.blogapp.sistemablog.repository;

import com.blogapp.sistemablog.entity.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    @Query("SELECT f FROM Follower f WHERE f.follower.id = :idUserFollower")
    Follower findFollowerRequestById(@Param("idUserFollower") Long idUserFollower);
}
