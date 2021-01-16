package org.evrim.spring.exam.aop2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ParentBean implements InitializingBean, DisposableBean {

    @Autowired
    private IChildBean childBean2;

    @Value("${key1:default}")
    //@Value("#{childBean1.value}")
    //@Value("#{@childBean1.value}")
    //@Value("#{T(org.evrim.spring.exam.aop2.util.UtilityClass).sum(1,2)}")
    private String key1;

//    public ParentBean() {
//        System.out.println("ParentBean() called. " + this.toString());
//    }

    public ParentBean(IChildBean childBean) {
        this.childBean2 = childBean;
        System.out.println("ParentBean(childBean) called. " + this.toString());
    }

    @PostConstruct
    void postConstruct() {
        System.out.println("ParentBean.postConstruct called. " + this.toString());
    }

    @PreDestroy
    void preDestroy() {
        System.out.println("ParentBean.preDestroy called. " + this.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ParentBean.afterPropertiesSet called. " + this.toString());
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("ParentBean.destroy called. " + this.toString());
    }

    @Override
    public String toString() {
        return "ParentBean{" +
                "childBean=" + childBean2 +
                ", key1=" + key1 +
                '}';
    }
}
