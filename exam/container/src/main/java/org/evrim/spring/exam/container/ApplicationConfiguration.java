package org.evrim.spring.exam.container;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("application.properties")
public class ApplicationConfiguration {
}
