package org.evrim.spring.exam.mvc.tomcat;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();

        context.setServletContext(servletContext);
        context.register(ApplicationConfig.class);
        context.refresh();

        ServletRegistration.Dynamic servlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        servlet.addMapping("/*");

        servlet.setLoadOnStartup(1);

    }
}
