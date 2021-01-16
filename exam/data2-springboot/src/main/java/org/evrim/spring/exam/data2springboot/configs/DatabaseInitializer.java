package org.evrim.spring.exam.data2springboot.configs;

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
public class DatabaseInitializer {

    @Value("classpath:/employee-schema.sql")
    Resource employeeSchema;
    @Value("classpath:/employee-test-data.sql")
    Resource employeeData;
    @Value("classpath:/product-schema.sql")
    Resource productSchema;
    @Value("classpath:/product-test-data.sql")
    Resource productData;

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator());
        return dataSourceInitializer;
    }

    private ResourceDatabasePopulator resourceDatabasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        //populator.addScript(employeeSchema);
        populator.addScript(employeeData);
        populator.addScript(productSchema);
        populator.addScript(productData);
        return populator;
    }
}
