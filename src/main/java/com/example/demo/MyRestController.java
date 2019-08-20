package com.example.demo;

import com.example.demo.model.JsFrameworkEntity;
import com.example.demo.repository.JsFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
    @Autowired
    private JsFrameworkRepository jsFrameworkRepository;

    @RequestMapping("/hello")
    public String hello() {
        JsFrameworkEntity one = jsFrameworkRepository.getOne(1l);
        return "Hello Gradle! > " + one;
    }

}
