package com.example.demo.repository;

import com.example.demo.model.JavaScriptFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JavaScriptFrameworkRepository extends JpaRepository<JavaScriptFramework, Long> {
}
