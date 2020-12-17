package org.evrim.spring.exam.data.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class EmployeeDataSourceConfig {

    @Bean
    public DataSource employeeDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:hsqldb:mem:localhost");
        return dataSource;
    }


    @Bean
    @Autowired
    public PlatformTransactionManager employeeTransactionManager(DataSource employeeDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(employeeDataSource);
        transactionManager.setDefaultTimeout(1000);
        return transactionManager;
    }

}
