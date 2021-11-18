package com.baeldung.um.spring;

import com.baeldung.client.spring.CommonClientConfig;
import com.baeldung.common.spring.CommonWebConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({ "com.baeldung.um.client" })
@Import({ CommonClientConfig.class, CommonWebConfig.class })
public class UmClientConfig {

    public UmClientConfig() {
        super();
    }

}
