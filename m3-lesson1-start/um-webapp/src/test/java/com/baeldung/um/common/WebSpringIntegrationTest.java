package com.baeldung.um.common;

import com.baeldung.um.spring.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { // @formatter:off
        UmContextConfig.class,

        UmPersistenceJpaConfig.class,

        UmServiceConfig.class,

        UmWebConfig.class,
        
        UmJavaSecurityConfig.class
})// @formatter:on
@WebAppConfiguration
public class WebSpringIntegrationTest {

    @Test
    public final void whenContextIsBootstrapped_thenOk() {
        //
    }

}
