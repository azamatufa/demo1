package com.example.demo.backend.service;

import org.springframework.data.domain.Pageable;

/**
 * @author almukhametovar
 */
public class PageQuery {
    public Pageable pageable;
    public int pageOffset;

    @Override
    public String toString() {
        return "PageQuery{" +
                "pageable=" + pageable +
                ", pageOffset=" + pageOffset +
                '}';
    }
}
