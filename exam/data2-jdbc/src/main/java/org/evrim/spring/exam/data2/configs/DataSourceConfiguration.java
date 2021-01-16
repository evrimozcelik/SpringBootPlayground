package org.evrim.spring.exam.data2.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DataSourceConfiguration {

    @Value("classpath:/employee-schema.sql")
    Resource employeeSchema;
    @Value("classpath:/employee-test-data.sql")
    Resource employeeData;
    @Value("classpath:/product-schema.sql")
    Resource productSchema;
    @Value("classpath:/product-test-data.sql")
    Resource productData;

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .addScript(employeeSchema.getFilename())
                .addScript(employeeData.getFilename())
                .addScript(productSchema.getFilename())
                .addScript(productData.getFilename())
                .build();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }
}
