package com.baeldung.um.dto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Map;

import com.baeldung.um.spring.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.baeldung.um.web.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UmApp.class})
@WebAppConfiguration
public class UserDtoIntegrationTest {

    private static final String URI = "/users";
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setupTemplate() {
        this.mockMvc = MockMvcBuilders.
                webAppContextSetup(wac)
                .build();
    }

    @Test
    public void whenUserIsCreatedWithValidAgeUsernameAndEmail_then201Created() throws Exception {
        UserDto user = createValidUser();

        String userObj = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = this.mockMvc.perform(post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userObj))
                .andReturn();

        Assert.assertEquals(201, mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenUserIsCreatedWithInvalidEmailAddresses_then400BadRequest() throws Exception {
        UserDto user = createValidUser();
        user.setEmail("invalid_email.com");

        String userObj = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = this.mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userObj))
                .andReturn();

        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenUserIsCreatedWithInvalidAge_then400BadRequest() throws Exception {
        UserDto user = createValidUser();
        user.setAge(-1);

        String userObj = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = this.mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userObj))
                .andReturn();

        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    @Test
    public void whenUserIsCreatedWithInvalidUsername_then400BadRequest() throws Exception {
        UserDto user = createValidUser();
        user.setName("n");

        String userObj = objectMapper.writeValueAsString(user);
        MvcResult mvcResult = this.mockMvc.perform(post(URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userObj))
                .andReturn();

        Assert.assertEquals(400, mvcResult.getResponse().getStatus());
    }

    private UserDto createValidUser() {
        UserDto user = new UserDto();
        user.setName("validemail");
        user.setEmail("validemail@fake.com");
        user.setPassword("userpass");
        user.setAge(20);
        return user;
    }
}
