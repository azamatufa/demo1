package com.example.demo.backend.endpoint;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.model.PageSizeWrapper;
import com.example.demo.backend.service.JavaScriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JavaScriptFrameworkController {
    @Autowired
    private JavaScriptFrameworkService service;

    // CRUD

    /**
     * [C]reate
     * @param framework item to create
     * @return created item
     */
    @RequestMapping(value = "/framework", method = RequestMethod.POST)
    public JavaScriptFramework create(@RequestBody JavaScriptFramework framework) {
        return service.create(framework);
    }

    /**
     * [R]ead
     * @param id id
     * @return item
     */
    @RequestMapping(value = "/framework", method = RequestMethod.GET)
    public JavaScriptFramework read(@RequestParam(name = "id") Long id) {
        return service.read(id);
    }

    /**
     * [U]pdate
     * @param framework item to update
     * @return updated item
     */
    @RequestMapping(value = "/framework", method = RequestMethod.PUT)
    public JavaScriptFramework update(@RequestBody JavaScriptFramework framework) {
        return service.update(framework);
    }

    /**
     * [D]elete
     * @param id of item to delete
     */
    @RequestMapping(value = "/framework", method = RequestMethod.DELETE)
    public void delete(@RequestParam(name = "id") Long id) {
        service.delete(id);
    }


    /**
     * Get one page of data
     *
     * @param startIndex start index
     * @param limit limit
     * @param filterString filter string
     * @param orderByClause special format, e.g. "name:ASCENDING,age:DESCENDING"
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<JavaScriptFramework> list(
            @RequestParam(value = "startIndex", defaultValue = "0", required = false) int startIndex,
            @RequestParam("limit") int limit,
            @RequestParam("filterString") String filterString,
            @RequestParam("orderByClause") String orderByClause) {
        return service.getPage(startIndex, limit, filterString, orderByClause);
    }

    /**
     * Count of items for specified filter
     *
     * @param filterString filter string
     * @return count of items for specified filter
     */
    @RequestMapping(value = "/size", method = RequestMethod.GET)
    public PageSizeWrapper size(
            @RequestParam(value = "filterString", defaultValue = "", required = false) String filterString) {
        Integer size = service.getPageSize(filterString);
        return new PageSizeWrapper(size);
    }

    /**
     *  Simple hello
     * @return hello
     */
    @RequestMapping("/hello")
    public String hello() {
        JavaScriptFramework one = service.read(1L);
        return "Hello Gradle! > " + one;
    }

}
