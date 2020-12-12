package org.evrim.spring.exam.container.service;

import org.evrim.spring.exam.container.ApplicationConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@ActiveProfiles(profiles = "external")
public class ExternalServiceTest {

    @Autowired
    private IService service;

    @Test
    public void process() {

        Assert.assertNotNull(service);
        Assert.assertTrue(service instanceof ExternalService);

        String request = "message";
        String response = service.process(request);

        Assert.assertTrue(response.contains("External"));
        Assert.assertTrue(response.contains(request));


    }
}