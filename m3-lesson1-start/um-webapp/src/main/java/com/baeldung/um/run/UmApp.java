package com.baeldung.um.run;

import com.baeldung.um.persistence.setup.MyApplicationContextInitializer;
import com.baeldung.um.spring.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = { // @formatter:off
}) // @formatter:on
@Import({ // @formatter:off
    UmContextConfig.class,
    UmPersistenceJpaConfig.class,
    UmServiceConfig.class,
    UmWebConfig.class,   
    UmJavaSecurityConfig.class
}) // @formatter:on
public class UmApp {

    public UmApp() {
        super();
    }

    //

    public static void main(final String... args) {
        new SpringApplicationBuilder(UmApp.class).initializers(new MyApplicationContextInitializer())
            .run(args);
    }

}
