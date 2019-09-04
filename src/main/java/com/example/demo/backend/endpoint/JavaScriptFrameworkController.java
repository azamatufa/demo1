package com.example.demo.backend.endpoint;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.model.PageSizeWrapper;
import com.example.demo.backend.service.JavaScriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JavaScriptFrameworkController {
    @Autowired
    private JavaScriptFrameworkService javaScriptFrameworkService;

    // TODO: 23.08.2019 Filter as object (not String), sorting as some class too...

    @RequestMapping("list")
    public List<JavaScriptFramework> list(
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) int startIndex,
            @RequestParam("limit") int limit,
            @RequestParam("filterString") String filterString,
            @RequestParam("orderByClause") String orderByClause) {
        return javaScriptFrameworkService.getList(startIndex, limit, filterString, orderByClause);
    }

    @RequestMapping("size")
    public PageSizeWrapper size(
            @RequestParam(value = "filterString", defaultValue = "", required = false) String filterString) {
        Integer size = javaScriptFrameworkService.getListSize(filterString);
        return new PageSizeWrapper(size);
    }

    @RequestMapping("/hello")
    public String hello() {
        JavaScriptFramework one = javaScriptFrameworkService.get(1L);
        return "Hello Gradle! > " + one;
    }

}
