package org.evrim.spring.exam.data.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.datasource.init.ScriptException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class EmployeeTestDataConfiguration {

    @Value("classpath:/employee-schema.sql")
    private Resource schema;

    @Value("classpath:/employee-test-data.sql")
    private Resource data;

    @Bean
    @Autowired
    DataSourceInitializer dataSourceInitializer(DataSource employeeDataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(employeeDataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        return  dataSourceInitializer;
    }

    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator databasePopulator =  new ResourceDatabasePopulator();
        databasePopulator.addScript(schema);
        databasePopulator.addScript(data);
        return databasePopulator;
    }

}
