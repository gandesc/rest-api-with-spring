package com.baeldung.um.mock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.baeldung.um.spring.UmApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UmApp.class)
@AutoConfigureMockMvc
public class SpringHttpMessageConverterMockTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenAllPrivilegesAreRetrived_then200K() throws Exception {
        // @formatter:off
        mockMvc.perform(get("/api/privileges")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
     // @formatter:on
    }
}