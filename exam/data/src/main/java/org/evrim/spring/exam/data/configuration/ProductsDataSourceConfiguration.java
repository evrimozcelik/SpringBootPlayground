package org.evrim.spring.exam.data.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.orm.jpa.JpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableJpaRepositories(basePackages = "org.evrim.spring.exam.data.dao",
        entityManagerFactoryRef = "productsEntityManagerFactory",
        transactionManagerRef = "productsTransactionManager"
)
@Configuration
public class ProductsDataSourceConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean productsEntityManagerFactory(DataSource productsDataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(productsDataSource);
        emf.setPackagesToScan("org.evrim.spring.exam.data.ds");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        return emf;
    }

    @Bean
    public PlatformTransactionManager productsTransactionManager(EntityManagerFactory productsEntityManagerFactory) {
        return new JpaTransactionManager(productsEntityManagerFactory);
    }

    @Bean
    public DataSource productsDataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setScriptEncoding("UTF-8")
                .addScript("product-schema.sql")
                .addScript("product-test-data.sql")
                .build();
    }

}
