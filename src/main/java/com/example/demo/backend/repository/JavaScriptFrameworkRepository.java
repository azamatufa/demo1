package com.example.demo.backend.repository;

import com.example.demo.backend.model.JavaScriptFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface JavaScriptFrameworkRepository extends JpaRepository<JavaScriptFramework, Long>, JpaSpecificationExecutor<JavaScriptFramework> {
}
