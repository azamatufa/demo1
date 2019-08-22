package com.example.demo;

import com.example.demo.model.HypeLevel;
import com.example.demo.model.JavaScriptFramework;
import com.example.demo.repository.JavaScriptFrameworkRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class MockDataGenerator {
    private final JavaScriptFrameworkRepository javaScriptFrameworkRepository;

    public MockDataGenerator(JavaScriptFrameworkRepository javaScriptFrameworkRepository) {
        this.javaScriptFrameworkRepository = javaScriptFrameworkRepository;
    }

    @PostConstruct
    private void generateMockData() {
        for (int i = 0; i < 10; i++) {
            JavaScriptFramework entity = new JavaScriptFramework("JSF_" + i,
                    String.valueOf(ThreadLocalRandom.current().nextInt(1, 5)),
                    randomLocalDate(), randomHypeLevel()
            );
            JavaScriptFramework saved = javaScriptFrameworkRepository.save(entity);
            System.out.println(saved);
        }
    }

    private HypeLevel randomHypeLevel() {
        HypeLevel[] hypes = HypeLevel.values();
        if (hypes.length == 0) return null;
        return hypes[ThreadLocalRandom.current().nextInt(0, hypes.length)];
    }


    private LocalDate randomLocalDate() {
        int beginDay = (int) LocalDate.of(2016, Month.JANUARY, 1).toEpochDay();
        int endDay = (int) LocalDate.now().toEpochDay();
        int randomDay = ThreadLocalRandom.current().nextInt(beginDay, endDay);
        return LocalDate.ofEpochDay(randomDay);
    }

}
