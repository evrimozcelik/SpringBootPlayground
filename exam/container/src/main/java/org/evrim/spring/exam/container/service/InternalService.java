package org.evrim.spring.exam.container.service;

import org.evrim.spring.exam.container.beans.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("internal")
@Service
public class InternalService implements IService {

    @Autowired
    private UserContext userContext;

    @Override
    public String process(String message) {
        System.out.println("Processing request for user " + userContext.getName());
        return "InternalService response for " + message;
    }
}
