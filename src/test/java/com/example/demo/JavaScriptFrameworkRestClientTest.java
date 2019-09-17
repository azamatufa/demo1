package com.example.demo;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.model.PageSizeWrapper;
import com.example.demo.frontend_vaadin.javascript_framework_crud.JavaScriptFrameworkRestClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Slf4j
public class JavaScriptFrameworkRestClientTest {

    final String BASE_URL = "http://localhost:8080/framework/";
    final String URL_ID_PATTERN = "http://localhost:8080/framework/?id=%s";


    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    JavaScriptFrameworkRestClient client;

    @Test
    public void deleteTest() {
        String url = String.format(URL_ID_PATTERN, 1);
        testRestTemplate.delete(url);
        JavaScriptFramework javaScriptFramework = get(1l);
        assertNull(javaScriptFramework);
    }


    @Test
    public void updateTest() {
        JavaScriptFramework javaScriptFramework = get(1L);

        javaScriptFramework.setFrameworkName("abc");
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        testRestTemplate.patchForObject(BASE_URL, javaScriptFramework, JavaScriptFramework.class);

        JavaScriptFramework updated = get(1L);
        assertThat(updated.getFrameworkName(), is("abc"));

    }

    @Test
    public void getTest() {
        JavaScriptFramework javaScriptFramework = get(1L);
        assertThat(javaScriptFramework.getId(), is(1L));
    }

    @Test
    public void readNotFoundTest() {
        JavaScriptFramework javaScriptFramework = get(666L);
        assertNull(javaScriptFramework);
    }

    @Test
    public void createTest() {
        JavaScriptFramework framework = new JavaScriptFramework();
        String millis = String.valueOf(System.currentTimeMillis());
        framework.setFrameworkName(millis);
        ResponseEntity<JavaScriptFramework> responseEntity = testRestTemplate.postForEntity(BASE_URL, framework, JavaScriptFramework.class);
        JavaScriptFramework javaScriptFramework = responseEntity.getBody();
        assert javaScriptFramework != null;
        assertThat(javaScriptFramework.getFrameworkName(), is(millis));
    }

    private JavaScriptFramework get(Long id) {
        final String URL = String.format(URL_ID_PATTERN, id);
        ResponseEntity<JavaScriptFramework> responseEntity = testRestTemplate.getForEntity(URL, JavaScriptFramework.class);
        return responseEntity.getBody();
    }

    @Test
    public void getPage_FiveItems() {
        String url = "http://localhost:8080/list/?startIndex=0&limit=5&filterString=&orderByClause=asd";
        JavaScriptFramework[] list = testRestTemplate.getForObject(url, JavaScriptFramework[].class);
        assertNotNull(list);
        assertTrue(list.length == 5);
        for (JavaScriptFramework javaScriptFramework : list) {
            log.info("{}", javaScriptFramework);
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
