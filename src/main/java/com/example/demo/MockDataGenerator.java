package com.example.demo;

import com.example.demo.model.JsFrameworkEntity;
import com.example.demo.repository.JsFrameworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class MockDataGenerator {
    private final JsFrameworkRepository jsFrameworkRepository;

    public MockDataGenerator(JsFrameworkRepository jsFrameworkRepository) {
        this.jsFrameworkRepository = jsFrameworkRepository;
    }

    @PostConstruct
    private void generateMockData() {
        for (int i = 0; i < 10; i++) {
            JsFrameworkEntity entity = new JsFrameworkEntity("aa");
            JsFrameworkEntity saved = jsFrameworkRepository.save(entity);
            System.out.println(saved);
        }
    }

}
