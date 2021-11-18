package com.baeldung.um.run;

import com.baeldung.um.persistence.setup.MyApplicationContextInitializer;
import com.baeldung.um.spring.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = { // @formatter:off
        SecurityAutoConfiguration.class
})// @formatter:on
@Import({// @formatter:off
        UmContextConfig.class,
        UmPersistenceJpaConfig.class,
        UmServiceConfig.class,
        UmWebConfig.class,
        UmJavaSecurityConfig.class
})
public class UmApp {

    public static void main(final String... args) {
        new SpringApplicationBuilder(UmApp.class).initializers(new MyApplicationContextInitializer())
                .run(args);
    }

}
