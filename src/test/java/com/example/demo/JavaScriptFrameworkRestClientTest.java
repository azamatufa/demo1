package com.example.demo;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.model.PageSizeWrapper;
import com.example.demo.frontend_vaadin.javascript_framework_crud.JavaScriptFrameworkRestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JavaScriptFrameworkRestClientTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    JavaScriptFrameworkRestClient client;

    @Test
    public void getPageTest() {
        System.out.println("--------------------Test");
        System.out.println("port " + port);
        String url = "http://localhost:8080/list/?startIndex=0&limit=5&filterString=&orderByClause=asd";
        JavaScriptFramework[] list = testRestTemplate.getForObject(url, JavaScriptFramework[].class);
        for (JavaScriptFramework javaScriptFramework : list) {
            System.out.println(javaScriptFramework);
        }


    }

    @Test
    public void getPageSizeTest() {
        String url_0 = "http://localhost:8080/size/?filterString=";
        String url_9 = "http://localhost:8080/size/?filterString=9";
        PageSizeWrapper size0 = testRestTemplate.getForObject(url_0, PageSizeWrapper.class);
        System.out.println(size0);
        PageSizeWrapper size9 = testRestTemplate.getForObject(url_9, PageSizeWrapper.class);
        System.out.println(size9);
    }
}
