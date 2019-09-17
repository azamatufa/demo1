package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.example.demo.backend.model.PageSizeWrapper;
import com.vaadin.spring.annotation.SpringComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Rest consumer client.
 * <p>
 * Demo task is to use separate REST service and client.
 */
@SpringComponent
public class JavaScriptFrameworkRestClient {
    private static final Logger log = LoggerFactory.getLogger(JavaScriptFrameworkRestClient.class);

    private static final String GET_FRAMEWORKS_URL_PATTERN = "http://localhost:8080/list/?startIndex=%s&limit=%s&filterString=%s&orderByClause=%s";
    private static final String GET_FRAMEWORKS_SIZE_URL_PATTERN = "http://localhost:8080/size/?filterString={0}";

    public JavaScriptFramework save(JavaScriptFramework javaScriptFramework) {
        //
    }

    public Stream<JavaScriptFramework> fetchFromBackEnd(int offset, int limit, String filterStr, String sorting) {
        log.info("fetchFromBackEnd. offset={}, limit={}, filterStr={}, sorting={}", offset, limit, filterStr, sorting);

        String getFrameworksUrl = String.format(GET_FRAMEWORKS_URL_PATTERN, offset, limit, filterStr, sorting);
//        String getFrameworksUrl = MessageFormat.format(GET_FRAMEWORKS_URL_PATTERN, offset, limit, filterStr, sorting);

        RestTemplate restTemplate = new RestTemplate();
        JavaScriptFramework[] frameworks = restTemplate.getForObject(getFrameworksUrl, JavaScriptFramework[].class);
        if (frameworks == null) {
            log.info("Null result fetched...");
            return Stream.empty();
        }

        return Arrays.stream(frameworks);
    }

    public int sizeInBackEnd(String filter) {
        log.info("sizeInBackEnd. filter={}", filter);
        String getPageSizeUrl = MessageFormat.format(GET_FRAMEWORKS_SIZE_URL_PATTERN, filter == null ? "" : filter);

        RestTemplate restTemplate = new RestTemplate();
        PageSizeWrapper pageSizeWrapper = restTemplate.getForObject(getPageSizeUrl, PageSizeWrapper.class);
        if (pageSizeWrapper == null || pageSizeWrapper.getPageSize() == null)
            return 0;
        return pageSizeWrapper.getPageSize();
    }
}
