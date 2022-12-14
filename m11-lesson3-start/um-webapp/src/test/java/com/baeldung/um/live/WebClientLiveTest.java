package com.baeldung.um.live;

import com.baeldung.um.persistence.model.Privilege;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { LiveTestConfig.class })
public class WebClientLiveTest {

    @Autowired
    private WebClient webClient;

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

}
