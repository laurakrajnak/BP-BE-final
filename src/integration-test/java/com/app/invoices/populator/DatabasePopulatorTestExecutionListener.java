package com.app.invoices.populator;

import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class DatabasePopulatorTestExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) {
        // Execute your populator logic before the test class
        // You can obtain the populator bean from the application context
        // and call the necessary methods to populate the database
        //DatabasePopulator populator = testContext.getApplicationContext().getBean(DatabasePopulator.class);
        //populator.afterPropertiesSet(testContext.getApplicationContext().getEnvironment());
    }

    // Other overridden methods are no-op

}
