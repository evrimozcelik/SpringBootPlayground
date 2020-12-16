package org.evrim.spring.exam.data.configuration;

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
public class TestDataConfiguration {

    @Value("classpath:/db-schema.sql")
    private Resource schema;

    @Value("classpath:/db-test-data.sql")
    private Resource data;

    @Bean
    DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
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
