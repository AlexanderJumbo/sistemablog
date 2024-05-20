package com.blogapp.sistemablog.repository.auth;

import com.blogapp.sistemablog.entity.security.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    @Query("SELECT o FROM Operation o WHERE o.permitAll = true")
    List<Operation> findPublicEndpoints();
}
