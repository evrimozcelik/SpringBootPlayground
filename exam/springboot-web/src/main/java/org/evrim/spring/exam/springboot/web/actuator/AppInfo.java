package org.evrim.spring.exam.springboot.web.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class AppInfo implements InfoContributor  {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("datacenter", "istanbul");
    }
}
