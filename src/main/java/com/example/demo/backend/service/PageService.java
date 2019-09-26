package com.example.demo.backend.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vaadin, almukhametovar
 */
@Component
public class PageService {

    /**
     * Creates the {@link org.springframework.data.domain.PageRequest} to use with Spring Repository.
     *
     *
     * @param offset offset (page number)
     * @param limit limit
     * @param sorting sorting string see
     * @return
     */
    public PageQuery getPaging(int offset, int limit, String sorting) {
        final PageQuery p = new PageQuery();
        int start = offset;
        int end = offset + limit;

        if (start < end - start) {
            p.pageable = PageRequest.of(0, end, getSorting(sorting));
            p.pageOffset = offset;
        } else {
            // Calculate the page that fits the full requested index range
            int size = end - start;
            while (start / size != (end - 1) / size) {
                ++size;
            }
            p.pageable = PageRequest.of(start / size, size, getSorting(sorting));
            // Set the offset on page to filter out unneeded results
            p.pageOffset = start % size;
        }

        return p;
    }


    /**
     * @author almukhametovar
     *
     * Parses string like this:
     * @param sorting e.g. "name:DESCENDING,surname:ASCENDING,sex:DESCENDING"
     * @return Sort
     */
    private Sort getSorting(String sorting) {
        List<Sort.Order> orders = Arrays.stream(sorting.split(","))
                .filter(part -> part.split(":").length > 1)
                .map(part -> {
                    String[] sort = part.split(":");
                    String property = sort[0];
                    String directionStr = sort[1];
                    Sort.Direction direction = "DESCENDING".equalsIgnoreCase(directionStr) ? Sort.Direction.DESC : Sort.Direction.ASC;
                    return new Sort.Order(direction, property);
                })
                .collect(Collectors.toList());
        return Sort.by(orders);
    }

}
