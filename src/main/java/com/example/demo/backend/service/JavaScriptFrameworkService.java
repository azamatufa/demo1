package com.example.demo.backend.service;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.repository.JavaScriptFrameworkRepository;
import com.example.demo.backend.repository.JavaScriptFrameworkSpecification;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *  Service Layer for Rest Controller
 *  see javadoc: {@link com.example.demo.backend.endpoint.JavaScriptFrameworkController}
 */
@Component
@Slf4j
public class JavaScriptFrameworkService {
    private final JavaScriptFrameworkRepository repository;
    private final PageService pageService;

    @Autowired
    public JavaScriptFrameworkService(JavaScriptFrameworkRepository repository, PageService pageService) {
        this.repository = repository;
        this.pageService = pageService;
    }


    public JavaScriptFramework create(JavaScriptFramework framework) {
        Preconditions.checkNotNull(framework, "framework cannot be null.");
        framework.setId(null);
        return repository.save(framework);
    }

    public JavaScriptFramework read(Long id) {
        log.info("read started. id={}", id);
        try {
            JavaScriptFramework framework = repository.findById(id).orElse(null);
            log.info("found={}", framework);
            return framework;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public JavaScriptFramework update(JavaScriptFramework framework) {
        Preconditions.checkNotNull(framework, "framework cannot be null.");
        Preconditions.checkArgument(framework.getId() != null, "framework id=null. cannot update.");
        return repository.save(framework);
    }

    public void delete(Long id) {
        log.info("delete started. id={}", id);
        repository.deleteById(id);
        log.info("delete finished");
    }


    public List<JavaScriptFramework> getPage(int startIndex, int limit, String filterStr, String orderByClause) {
        PageQuery pageQuery = pageService.getPaging(startIndex, limit, orderByClause);

        Page<JavaScriptFramework> page = repository.findAll(
                JavaScriptFrameworkSpecification.withFilter(filterStr),
                pageQuery.pageable);
        return
                StreamSupport.stream(page.spliterator(), false)
                        .skip(pageQuery.pageOffset)
                        .limit(limit)
                        .collect(Collectors.toList());
    }


    public Integer getPageSize(String filterString) {
        return Math.toIntExact(repository.count(JavaScriptFrameworkSpecification.withFilter(filterString)));
    }

}
