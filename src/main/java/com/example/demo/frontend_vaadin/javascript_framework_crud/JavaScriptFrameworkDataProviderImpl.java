package com.example.demo.frontend_vaadin.javascript_framework_crud;

import com.example.demo.backend.model.JavaScriptFramework;
import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import com.vaadin.shared.data.sort.SortDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

/**
 * DataProvider is used as datasource by {@link com.vaadin.ui.Grid}
 */
@Component
public class JavaScriptFrameworkDataProviderImpl extends AbstractBackEndDataProvider<JavaScriptFramework, String> implements JavaScriptFrameworkDataProvider {

    @Autowired
    private JavaScriptFrameworkRestClient restClient;


    @Override
    protected Stream<JavaScriptFramework> fetchFromBackEnd(Query<JavaScriptFramework, String> query) {
        List<QuerySortOrder> sortOrders = query.getSortOrders();
        StringBuilder orderByClause = new StringBuilder();
        for (QuerySortOrder sortOrder : sortOrders) {
            String property = sortOrder.getSorted();
            SortDirection direction = sortOrder.getDirection();
            orderByClause.append(property).append(":").append(direction).append(",");
        }
        // remove last comma
        if (orderByClause.length() > 1) {
            orderByClause.deleteCharAt(orderByClause.length() - 1);
        }

        return restClient.fetchFromBackEnd(query.getOffset(), query.getLimit(), query.getFilter().orElse(""), orderByClause.toString());
    }

    @Override
    protected int sizeInBackEnd(Query<JavaScriptFramework, String> query) {
        return restClient.sizeInBackEnd(query.getFilter().orElse(""));
    }

}
