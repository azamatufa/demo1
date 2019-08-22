package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author almukhametovar
 */
@SpringBootApplication
@EntityScan({"com.example.demo"})
@EnableJpaRepositories({"com.example.demo"})
//@ComponentScan({"com.example.demo"})
/*
* @EnableJpaRepositories({"ru.bashneft.adminpanel"})
@ComponentScan({"ru.bashneft.adminpanel"})
@EntityScan({"ru.bashneft.adminpanel"})
* */
public class JavaScriptFrameworkApplication /*extends SpringBootServletInitializer*/ {
    public static void main(String[] args) {
        SpringApplication.run(JavaScriptFrameworkApplication.class, args);
    }
}
