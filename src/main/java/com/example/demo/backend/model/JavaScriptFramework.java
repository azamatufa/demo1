package com.example.demo.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
public class JavaScriptFramework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String frameworkName;
    private String version;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate deprecationDate;

    private HypeLevel hypeLevel;


}


