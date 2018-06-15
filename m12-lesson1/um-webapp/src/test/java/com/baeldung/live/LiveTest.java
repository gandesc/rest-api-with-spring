package com.baeldung.live;

import java.util.Arrays;
import java.util.TreeSet;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.baeldung.common.util.RandomStringUtils;
import com.baeldung.um.persistence.model.User;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class LiveTest {

    private final static String URI = "http://localhost:8082/um-webapp/api/users";

    @Test
    public void whenUserIsCreatedWithInvalidAlternativeEmailAddresses_then400BadRequest() {
        User user = userWithInvalidEmailAddresses();
        final Response response = RestAssured.given().contentType(ContentType.JSON).body(user).post(URI);

        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(400));
    }

    @Test
    public void whenUserIsCreatedWithValidAlternativeEmailAddresses_then201Created() {
        User user = userWithValidEmailAddresses();
        final Response response = RestAssured.given().contentType(ContentType.JSON).body(user).post(URI);

        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
    }

    @Test
    public void whenUserIsCreatedWithInvalidAge_then400BadRequest() {
        User user = userWithInvalidAge();
        final Response response = RestAssured.given().contentType(ContentType.JSON).body(user).post(URI);

        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(400));
    }

    @Test
    public void whenUserIsCreatedWithValidAge_then201Created() {
        User user = userWithValidAge();
        final Response response = RestAssured.given().contentType(ContentType.JSON).body(user).post(URI);

        Assert.assertThat(response.getStatusCode(), Matchers.equalTo(201));
    }

    private User userWithInvalidEmailAddresses() {
        User user = new User();
        user.setName("newUser1");
        user.setEmail("newuser1@fake.com");
        user.setPassword("userpass");
        user.setAlternativeEmailAddresses(new TreeSet<String>(Arrays.asList("email1@fake.com", "email2fake.com")));
        return user;
    }

    private User userWithValidEmailAddresses() {
        User user = new User();
        String randomUserName = RandomStringUtils.randomAlphabetic(5);
        user.setName(randomUserName);
        user.setEmail(randomUserName + "@fake.com");
        user.setPassword("userpass");
        user.setAlternativeEmailAddresses(new TreeSet<String>(Arrays.asList("email1@fake.com", "email2@fake.com")));
        return user;
    }

    private User userWithInvalidAge() {
        User user = new User();
        user.setName("newUser3");
        user.setEmail("newuser2@fake.com");
        user.setPassword("userpass");
        user.setAge(0L);
        user.setAlternativeEmailAddresses(new TreeSet<String>(Arrays.asList("email1@fake.com", "email2@fake.com")));
        return user;
    }

    private User userWithValidAge() {
        User user = new User();
        String randomUserName = RandomStringUtils.randomAlphabetic(5);
        user.setName(randomUserName);
        user.setEmail(randomUserName + "@fake.com");
        user.setPassword("userpass");
        user.setAge(16L);
        user.setAlternativeEmailAddresses(new TreeSet<String>(Arrays.asList("email1@fake.com", "email2@fake.com")));
        return user;
    }

}
