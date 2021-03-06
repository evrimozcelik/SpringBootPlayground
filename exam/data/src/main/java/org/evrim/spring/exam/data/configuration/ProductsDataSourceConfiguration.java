package org.evrim.spring.exam.data.configuration;


import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Autowired;
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
        entityManagerFactoryRef = "productsEntityManagerFactory"
)
@Configuration
public class ProductsDataSourceConfiguration {

    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean productsEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("org.evrim.spring.exam.data.ds");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        emf.setJpaVendorAdapter(vendorAdapter);

        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory productsEntityManagerFactory) {
        return new JpaTransactionManager(productsEntityManagerFactory);
    }

    //@Bean
//    public DataSource productsDataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .setScriptEncoding("UTF-8")
//                .addScript("product-schema.sql")
//                .addScript("product-test-data.sql")
//                .build();
//
//    }

}
