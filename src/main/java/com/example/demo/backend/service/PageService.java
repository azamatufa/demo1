package com.example.demo.backend.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @author Vaadin Demo
 */
@Component
public class PageService {

    public PageQuery getPaging(int offset, int limit) {
        final PageQuery p = new PageQuery();
        int start = offset;
        int end = offset + limit;

        if (start < end - start) {
            p.pageable = PageRequest.of(0, end, Sort.by("id"));
            p.pageOffset = offset;
        } else {
            // Calculate the page that fits the full requested index range
            int size = end - start;
            while (start / size != (end - 1) / size) {
                ++size;
            }
            p.pageable = PageRequest.of(start / size, size, Sort.by("id"));
            // Set the offset on page to filter out unneeded results
            p.pageOffset = start % size;
        }

        return p;
    }

//    private PageRequest getPageRequest(Query q, int pageIndex, int pageLength) {
//        if (!q.getSortOrders().isEmpty()) {
//            return PageRequest.of(pageIndex, pageLength, getSorting(q));
//
//        } else {
//            return PageRequest.of(pageIndex, pageLength);
//        }
//    }
//
//    private <T, F> Sort getSorting(Query<T, F> q) {
//        return Sort.by(q.getSortOrders().stream()
//                .map(so -> new Sort.Order(
//                        so.getDirection() == SortDirection.ASCENDING
//                                ? Sort.Direction.ASC : Sort.Direction.DESC,
//                        so.getSorted()))
//                .collect(Collectors.toList()));
//    }

}
