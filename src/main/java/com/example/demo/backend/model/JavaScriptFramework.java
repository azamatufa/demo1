package com.example.demo.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JavaScriptFramework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String frameworkName;
    private String version;
    private LocalDate deprecationDate;
    private HypeLevel hypeLevel;


}


