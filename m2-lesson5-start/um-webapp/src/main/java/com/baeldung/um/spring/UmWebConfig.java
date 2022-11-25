package com.baeldung.um.spring;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Configuration
@ComponentScan({"com.baeldung.um.web", "com.baeldung.common.web"})
@EnableWebMvc
public class UmWebConfig implements WebMvcConfigurer {

    // configuration

    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        final Optional<HttpMessageConverter<?>> jsonConverterFound = converters.stream()
                .filter(c -> c instanceof MappingJackson2HttpMessageConverter)
                .findFirst();
        if (jsonConverterFound.isPresent()) {
            final AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) jsonConverterFound.get();
            converter.getObjectMapper()
                    .enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper()
                    .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }

        final Optional<HttpMessageConverter<?>> xmlConverterFound = converters.stream()
                .filter(c -> c instanceof MappingJackson2XmlHttpMessageConverter)
                .findFirst();
        if (xmlConverterFound.isPresent()) {
            final AbstractJackson2HttpMessageConverter converter = (AbstractJackson2HttpMessageConverter) xmlConverterFound.get();
            converter.getObjectMapper()
                    .enable(SerializationFeature.INDENT_OUTPUT)
                    .enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }
    }
}
