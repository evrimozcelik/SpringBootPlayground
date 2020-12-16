package org.evrim.spring.exam.aop.services;

import org.evrim.spring.exam.aop.beans.MyBean;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public void register(MyBean bean) {
        System.out.format("%s %s registered", bean.getName(), bean.getSurname());
    }
}
