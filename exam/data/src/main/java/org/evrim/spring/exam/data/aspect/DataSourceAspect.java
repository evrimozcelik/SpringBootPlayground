package org.evrim.spring.exam.data.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;

@Aspect
@Component
public class DataSourceAspect {

    private final String[] traceMethods = new String[] {"commit", "rollback", "close"};

    @Around("target(javax.sql.DataSource)")
    public Object aroundDataSource(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("Data Source Trace: " + proceedingJoinPoint.getSignature());

        Object object = proceedingJoinPoint.proceed();
        if(object instanceof Connection) {
            object = proxyConnection((Connection) object);
        }

        return object;
    }

    private Connection proxyConnection(Connection connection) {
        return (Connection)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[] {Connection.class},
                (proxy, method, objects) -> {
                    String methodName = method.getName();
                    boolean shouldTraceLog = Arrays.stream(traceMethods).anyMatch(s -> s.equalsIgnoreCase(methodName));
                    if(shouldTraceLog) {
                        System.out.println("Connection Trace: " + method.toGenericString());
                    }
                    return method.invoke(connection, objects);
                });
    }
}
