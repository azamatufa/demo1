package com.example.demo.backend.repository;

import com.example.demo.backend.model.JavaScriptFramework;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class with Specifications
 */
public class JavaScriptFrameworkSpecification {

    /**
     * Specification to search by "frameworkName"
     *
     * @param filterString filter
     * @return spec
     */
    public static Specification<JavaScriptFramework> withFilter(String filterString) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filterString != null && !filterString.isEmpty()) {
                Path<String> frameworkNamePath = root.get("frameworkName");
                Predicate like = criteriaBuilder.like(frameworkNamePath, "%" + filterString + "%");
                predicates.add(like);
            }

            Predicate[] predicatesArray = predicates.toArray(new Predicate[0]);
            CriteriaQuery<?> where = query.where(predicatesArray);
            return where.getRestriction();
        };
    }
}
