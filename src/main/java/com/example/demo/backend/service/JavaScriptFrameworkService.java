package com.example.demo.backend.service;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.repository.JavaScriptFrameworkRepository;
import com.example.demo.backend.repository.JavaScriptFrameworkSpecification;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JavaScriptFrameworkService {
    private final JavaScriptFrameworkRepository repository;
    private final PageService pageService;

    @Autowired
    public JavaScriptFrameworkService(JavaScriptFrameworkRepository repository, PageService pageService) {
        this.repository = repository;
        this.pageService = pageService;
    }

    public String hello() {
        return "hello";
    }

    public JavaScriptFramework update(JavaScriptFramework framework) {
        Preconditions.checkNotNull(framework, "framework cannot be null.");
        Preconditions.checkArgument(framework.getId() != null, "framework id=null. cannot update.");
        return repository.save(framework);
    }

    public JavaScriptFramework create(JavaScriptFramework framework) {
        Preconditions.checkNotNull(framework, "framework cannot be null.");
        framework.setId(null);
        return repository.save(framework);
    }

    public JavaScriptFramework get(Long id) {
        return repository.getOne(id);
    }

    public JavaScriptFramework save(JavaScriptFramework javaScriptFramework) {
        return repository.save(javaScriptFramework);
    }

    public List<JavaScriptFramework> getList(int startIndex, int limit, String filterStr, String orderByClause) {
        PageQuery pageQuery = pageService.getPaging(startIndex, limit);

        Page<JavaScriptFramework> page = repository.findAll(
                JavaScriptFrameworkSpecification.withFilter(filterStr),
                pageQuery.pageable);
        return
                StreamSupport.stream(page.spliterator(), false)
                        .skip(pageQuery.pageOffset)
                        .limit(limit)
                        .collect(Collectors.toList());
    }

    public Integer getListSize(String filterString) {
        return Math.toIntExact(repository.count(JavaScriptFrameworkSpecification.withFilter(filterString)));
    }


}
