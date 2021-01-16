package org.evrim.spring.exam.springboot;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    String propertyA;
    String propertyB;
    String propertyC;
    String propertyD;
    String propertyE;
    String propertyF;
}
