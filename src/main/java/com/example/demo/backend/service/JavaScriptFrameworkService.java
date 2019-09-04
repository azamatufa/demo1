package com.example.demo.backend.service;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.repository.JavaScriptFrameworkRepository;
import com.example.demo.backend.repository.JavaScriptFrameworkSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JavaScriptFrameworkService {
    private final JavaScriptFrameworkRepository javaScriptFrameworkRepository;
    private final PageService pageService;

    @Autowired
    public JavaScriptFrameworkService(JavaScriptFrameworkRepository javaScriptFrameworkRepository, PageService pageService) {
        this.javaScriptFrameworkRepository = javaScriptFrameworkRepository;
        this.pageService = pageService;
    }

    public String hello() {
        return "hello";
    }

    public JavaScriptFramework get(Long id) {
        return javaScriptFrameworkRepository.getOne(id);
    }


    public List<JavaScriptFramework> getList(int startIndex, int limit, String filterStr, String orderByClause) {
        PageQuery pageQuery = pageService.getPaging(startIndex, limit);

        Page<JavaScriptFramework> page = javaScriptFrameworkRepository.findAll(
                JavaScriptFrameworkSpecification.withFilter(filterStr),
                pageQuery.pageable);
        return
                StreamSupport.stream(page.spliterator(), false)
                        .skip(pageQuery.pageOffset)
                        .limit(limit)
                        .collect(Collectors.toList());
    }

    public Integer getListSize(String filterString) {
        return Math.toIntExact(javaScriptFrameworkRepository.count(JavaScriptFrameworkSpecification.withFilter(filterString)));
    }


}
