package com.app.invoices.populator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Profile("development")
public class DatabasePopulator implements InitializingBean {

    private final ResourceLoader resourceLoader;
    private final DataSource dataSource;

    @Value("classpath:db/migration/V1__init_test_data.sql")
    private Resource initDataScript;

    public DatabasePopulator(ResourceLoader resourceLoader, DataSource dataSource) {
        this.resourceLoader = resourceLoader;
        this.dataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(initDataScript);
//
//        populator.populate(dataSource.getConnection());
//        System.out.println("Database populated successfully!");
    }
}
