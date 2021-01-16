package org.evrim.spring.exam.aop2.beans;


import org.evrim.spring.exam.aop2.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@TestPropertySource(properties = "key1=test-value1")
public class ParentBeanTest {

    @Autowired
    ParentBean parentBean;

    @Test
    public void test1() {

        System.out.println(parentBean.toString());

    }
}