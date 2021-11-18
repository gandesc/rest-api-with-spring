package com.baeldung.um.live;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.baeldung.um.persistence.model.Privilege;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { LiveTestConfig.class })
public class WebClientLiveTest {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    WebClient webClient;
    
    @Autowired
    WebTestClient webTestClient;

    @Test
    public void whenRetrievingAllPrivileges_thenOK() throws InterruptedException {
        Flux<Privilege> response = webClient.get()
            .uri("/privileges")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(Privilege.class);
        response.subscribe(privilege -> System.out.println(privilege));

        Thread.sleep(35000);
    }
    
    @Test
    public void whenCreatingNewPrivilege_thenOK() throws InterruptedException {
        Mono<HttpStatus> statusCode = webClient.post()
            .uri("/privileges")
            .syncBody(newPrivilege())
            .exchange()
            .map(resp -> resp.statusCode());
        statusCode.subscribe(status -> System.out.println("Status Code = " + status.value()));

        Thread.sleep(35000);
    }

    @Test
    public void whenRetrievingSinglePrivilege_thenOK() {
        webTestClient.get()
            .uri("/privileges/1")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectBody(Privilege.class)
            .isEqualTo(firstPrivilege());
    }
    
    private Privilege firstPrivilege() {
        Privilege privilege = new Privilege("ROLE_PRIVILEGE_READ");
        privilege.setId(1L);
        return privilege;
    }

    private Privilege newPrivilege() {
        return new Privilege(RandomStringUtils.randomAlphabetic(5));
    }

}
