package org.baeldung.um.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import org.baeldung.um.persistence.setup.MyApplicationContextInitializer;
import org.baeldung.um.spring.UmContextConfig;
import org.baeldung.um.spring.UmPersistenceJpaConfig;
import org.baeldung.um.spring.UmServiceConfig;
import org.baeldung.um.spring.UmServletConfig;
import org.baeldung.um.spring.UmWebConfig;

@SpringBootApplication(exclude = { // @formatter:off
        ErrorMvcAutoConfiguration.class
})// @formatter:on
public class UmApp extends SpringBootServletInitializer {

    private final static Object[] CONFIGS = { // @formatter:off
            UmContextConfig.class,
            UmPersistenceJpaConfig.class,
            UmServiceConfig.class,
            UmWebConfig.class,
            UmServletConfig.class,

            UmApp.class
    }; // @formatter:on

    //

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(CONFIGS).initializers(new MyApplicationContextInitializer());
    }

    public static void main(final String... args) {
        final SpringApplication springApplication = new SpringApplication(CONFIGS);
        springApplication.addInitializers(new MyApplicationContextInitializer());
        springApplication.run(args);
    }

}
