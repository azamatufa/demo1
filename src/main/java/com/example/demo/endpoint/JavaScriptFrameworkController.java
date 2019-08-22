package com.example.demo.endpoint;

import com.example.demo.model.JavaScriptFramework;
import com.example.demo.service.JavaScriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaScriptFrameworkController {
    @Autowired
    private JavaScriptFrameworkService javaScriptFrameworkService;

    @RequestMapping("/hello")
    public String hello() {
        JavaScriptFramework one = javaScriptFrameworkService.getOne(1l);
        return "Hello Gradle! > " + one;
    }

}
