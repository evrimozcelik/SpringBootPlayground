package org.evrim.spring.exam.aop2.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
//@Qualifier("second")
@Profile("second")
public class ChildBean2 implements IChildBean {

    public String value = "second";

}
