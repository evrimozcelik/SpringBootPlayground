package org.evrim.spring.exam.data.configuration;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.icatch.jta.UserTransactionImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

//@Configuration
public class JtaConfiguration {

    @Bean
    public UserTransaction atomikosUserTransaction() throws Throwable {
        UserTransaction userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(1000);
        return userTransaction;
    }

    @Bean
    public TransactionManager atomikosTransactionManager() {
        UserTransactionManager transactionManager = new UserTransactionManager();
        transactionManager.setForceShutdown(false);
        return transactionManager;
    }

    @Autowired
    @Bean
    public PlatformTransactionManager transactionManager(UserTransaction atomikosUserTransaction, TransactionManager atomikosTransactionManager) {
        return new JtaTransactionManager(atomikosUserTransaction, atomikosTransactionManager);
    }
}
