package com.example.demo.backend.endpoint;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.model.PageSizeWrapper;
import com.example.demo.backend.service.JavaScriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JavaScriptFrameworkController {
    @Autowired
    private JavaScriptFrameworkService service;

    // CRUD

    @RequestMapping(value = "framework", method = RequestMethod.POST)
    public JavaScriptFramework create(@RequestBody JavaScriptFramework framework) {
        return service.create(framework);
    }

    @RequestMapping(value = "framework", method = RequestMethod.GET)
    public JavaScriptFramework read(@RequestParam(name = "id") Long id) {
        return service.read(id);
    }

    @RequestMapping(value = "framework", method = RequestMethod.PUT)
    public JavaScriptFramework update(@RequestBody JavaScriptFramework framework) {
        return service.update(framework);
    }

    @RequestMapping(value = "framework", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "id") Long id) {
        service.delete(id);
    }



    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<JavaScriptFramework> list(
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) int startIndex,
            @RequestParam("limit") int limit,
            @RequestParam("filterString") String filterString,
            @RequestParam("orderByClause") String orderByClause) {
        return service.getList(startIndex, limit, filterString, orderByClause);
    }

    @RequestMapping(value = "size", method = RequestMethod.GET)
    public PageSizeWrapper size(
            @RequestParam(value = "filterString", defaultValue = "", required = false) String filterString) {
        Integer size = service.getListSize(filterString);
        return new PageSizeWrapper(size);
    }

    @RequestMapping("/hello")
    public String hello() {
        JavaScriptFramework one = service.read(1L);
        return "Hello Gradle! > " + one;
    }

}
