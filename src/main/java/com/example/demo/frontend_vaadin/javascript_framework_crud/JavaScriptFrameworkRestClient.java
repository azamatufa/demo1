package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.model.PageSizeWrapper;
import com.vaadin.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Rest consumer.
 * <p>
 * According to demo task, ui and backend should communicate using REST.
 */
@SpringComponent
public class JavaScriptFrameworkRestClient {
    private static final Logger log = LoggerFactory.getLogger(JavaScriptFrameworkRestClient.class);

    // POST - create, PATCH - update
    private static final String BASE_URL = "http://localhost:8080/framework/";

    // GET - read, DELETE - delete
    private static final String URL_ID_PATTERN = "http://localhost:8080/framework/?id=%s";

    // Paged data fetch url patterns
    private static final String GET_PAGE_URL_PATTERN = "http://localhost:8080/list/?startIndex=%s&limit=%s&filterString=%s&orderByClause=%s";
    private static final String GET_PAGE_SIZE_URL_PATTERN = "http://localhost:8080/size/?filterString={0}";


    public JavaScriptFramework create(JavaScriptFramework javaScriptFramework) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JavaScriptFramework> responseEntity = restTemplate.postForEntity(BASE_URL, javaScriptFramework, JavaScriptFramework.class);
        return responseEntity.getBody();
    }

    public JavaScriptFramework read(Long id) {
        String url = String.format(URL_ID_PATTERN, id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JavaScriptFramework> responseEntity = restTemplate.getForEntity(url, JavaScriptFramework.class);
        return responseEntity.getBody();
    }

    public JavaScriptFramework update(JavaScriptFramework javaScriptFramework) {
        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate.put(BASE_URL, javaScriptFramework);
        return read(javaScriptFramework.getId());
    }

    public void delete(Long id) {
        String url = String.format(URL_ID_PATTERN, id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
    }

    /**
     * Paged data fetch
     *
     * @param offset    offset number
     * @param limit     page's limit
     * @param filterStr critera
     * @param sorting   order by clause
     * @return
     */
    public Stream<JavaScriptFramework> fetchFromBackEnd(int offset, int limit, String filterStr, String sorting) {
        log.info("fetchFromBackEnd. offset={}, limit={}, filterStr={}, sorting={}", offset, limit, filterStr, sorting);

        String getFrameworksUrl = String.format(GET_PAGE_URL_PATTERN, offset, limit, filterStr, sorting);

        RestTemplate restTemplate = new RestTemplate();
        JavaScriptFramework[] frameworks = restTemplate.getForObject(getFrameworksUrl, JavaScriptFramework[].class);
        if (frameworks == null) {
            log.info("Null result fetched...");
            return Stream.empty();
        }

        return Arrays.stream(frameworks);
    }

    /**
     * Total items count with concrete criteria
     *
     * @param filter critera
     * @return size
     */
    public int sizeInBackEnd(String filter) {
        log.info("sizeInBackEnd. filter={}", filter);
        String getPageSizeUrl = MessageFormat.format(GET_PAGE_SIZE_URL_PATTERN, filter == null ? "" : filter);

        RestTemplate restTemplate = new RestTemplate();
        PageSizeWrapper pageSizeWrapper = restTemplate.getForObject(getPageSizeUrl, PageSizeWrapper.class);
        if (pageSizeWrapper == null || pageSizeWrapper.getPageSize() == null)
            return 0;
        return pageSizeWrapper.getPageSize();
    }
}
