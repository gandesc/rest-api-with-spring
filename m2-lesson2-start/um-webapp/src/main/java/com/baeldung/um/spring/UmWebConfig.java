package com.baeldung.um.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.validation.Validator;

@Configuration
@ComponentScan({ "com.baeldung.um.web", "com.baeldung.common.web" })
@EnableWebMvc
public class UmWebConfig implements WebMvcConfigurer {

    // configuration

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        final Optional<HttpMessageConverter<?>> converterFound = converters.stream()
            .filter(c -> c instanceof AbstractJackson2HttpMessageConverter)
            .findFirst();
        if (converterFound.isPresent()) {
            final AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) converterFound.get();
            converter.getObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper()
                .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }

    @Bean
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }
}
