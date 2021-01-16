package org.evrim.spring.exam.springboot.autoconfig.mysql;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;


@ConditionalOnClass(com.mysql.cj.jdbc.Driver.class)
@Configuration
public class MySqlAutoConfiguration implements InitializingBean  {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MySqlAutoConfiguration initializing");
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb?allowPublicKeyRetrieval=true&useSSL=false");
        dataSource.setUsername("dbuser");
        dataSource.setPassword("passw0rd");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    //@Bean
    DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("hsql-schema.sql"));
        populator.addScript(new ClassPathResource("hsql-data.sql"));

        initializer.setDatabasePopulator(populator);

        return initializer;
    }


}
