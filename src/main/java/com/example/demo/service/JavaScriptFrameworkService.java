package com.example.demo.service;

import com.example.demo.model.JavaScriptFramework;
import com.example.demo.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JavaScriptFrameworkService {
    private final JavaScriptFrameworkRepository javaScriptFrameworkRepository;

    @Autowired
    public JavaScriptFrameworkService(JavaScriptFrameworkRepository javaScriptFrameworkRepository) {
        this.javaScriptFrameworkRepository = javaScriptFrameworkRepository;
    }


    public JavaScriptFramework getOne(Long id) {
        return javaScriptFrameworkRepository.getOne(id);
    }

    public String hello() {
        return "hello";
    }
}
