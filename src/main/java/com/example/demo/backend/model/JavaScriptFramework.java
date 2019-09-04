package com.example.demo.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class JavaScriptFramework {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String frameworkName;
    private String version;
    private LocalDate deprecationDate;
    private HypeLevel hypeLevel;

    public JavaScriptFramework() {
    }

    public JavaScriptFramework(String frameworkName, String version, LocalDate deprecationDate, HypeLevel hypeLevel) {
        this.frameworkName = frameworkName;
        this.version = version;
        this.deprecationDate = deprecationDate;
        this.hypeLevel = hypeLevel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public LocalDate getDeprecationDate() {
        return deprecationDate;
    }

    public void setDeprecationDate(LocalDate deprecationDate) {
        this.deprecationDate = deprecationDate;
    }

    public HypeLevel getHypeLevel() {
        return hypeLevel;
    }

    public void setHypeLevel(HypeLevel hypeLevel) {
        this.hypeLevel = hypeLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JavaScriptFramework that = (JavaScriptFramework) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "JavaScriptFramework{" +
                "id=" + id +
                ", name='" + frameworkName + '\'' +
                ", version='" + version + '\'' +
                ", deprecationDate=" + deprecationDate +
                ", hypeLevel=" + hypeLevel +
                '}';
    }
}


