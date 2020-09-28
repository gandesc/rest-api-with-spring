package com.baeldung.um.web;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.baeldung.um.run.UmApp;
import com.baeldung.um.spring.UmContextConfig;
import com.baeldung.um.spring.UmPersistenceJpaConfig;
import com.baeldung.um.spring.UmServiceConfig;
import com.baeldung.um.spring.UmServletConfig;
import com.baeldung.um.spring.UmWebConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UmApp.class, UmWebConfig.class, UmServletConfig.class, UmServiceConfig.class, UmPersistenceJpaConfig.class, UmContextConfig.class }, webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestApiVersionLiveTest {

    private static final String RESOURCE_URI = "/countv";
    private static final String APP_ROOT = "/users";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGetResourceWithoutVersion_thenNotFound() {
        final ResponseEntity<String> response = restTemplate.getForEntity(APP_ROOT + RESOURCE_URI, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void whenGetResourceWithParamVersion_thenOk() {
        final ResponseEntity<String> response = restTemplate.getForEntity(APP_ROOT + RESOURCE_URI + "?v=1.0", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenGetResourceWithHeaderVersion_thenOk() {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.resource.v1.0+json");

        final HttpEntity<String> entity = new HttpEntity<>(headers);

        final ResponseEntity<String> response = restTemplate.exchange(APP_ROOT + RESOURCE_URI, HttpMethod.GET, entity, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void whenGetResourceWithUrlVersion_thenOk() {
        final ResponseEntity<String> response = restTemplate.getForEntity(APP_ROOT + "/v1.0" + RESOURCE_URI, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
