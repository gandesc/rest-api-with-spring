package com.baeldung.um.live;

import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.persistence.model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { LiveTestConfig.class })
public class WebClientLiveTest {

    @Autowired
    private WebClient webClient;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void whenRetrievingPrivileges_thenOk() throws InterruptedException {
        Flux<Privilege> result = webClient.get()
                .uri("/privileges")
                .accept(MediaType.valueOf(MediaType.TEXT_EVENT_STREAM_VALUE))
                .retrieve()
                .bodyToFlux(Privilege.class);

        result.subscribe(privilege -> System.out.println(privilege));

        Thread.sleep(35000);
    }

    @Test
    public void whenCreatingNewPrivilege_thenOk() throws InterruptedException {
        Mono<HttpStatus> result = webClient.post()
                .uri("/privileges")
                .bodyValue(newPrivilege())
                .exchangeToMono(resp -> Mono.just(resp.statusCode()));

        result.subscribe(status -> System.out.println(status.value()));

        Thread.sleep(2000);
    }

    @Test
    public void whenRetrievingSinglePrivilege_thenOk() throws InterruptedException {
        webTestClient.get()
                .uri("/privileges/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(Privilege.class)
                .isEqualTo(firstModel());

        Thread.sleep(2000);
    }

    private Privilege firstModel() {
        return new Privilege("ROLE_PRIVILEGE_READ");
    }

    private Object newPrivilege() {
        return new Privilege(RandomStringUtils.randomAlphabetic(15));
    }
}
