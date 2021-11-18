package com.baeldung.um.web.role;

import com.baeldung.common.web.WebConstants;
import com.baeldung.um.client.UmPaths;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.spring.CommonTestConfig;
import com.baeldung.um.spring.UmClientConfig;
import com.baeldung.um.spring.UmLiveTestConfig;
import com.baeldung.um.util.Um;
import com.google.common.collect.Sets;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpHeaders;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static com.baeldung.common.spring.util.Profiles.CLIENT;
import static com.baeldung.common.spring.util.Profiles.TEST;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@ActiveProfiles({ CLIENT, TEST })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UmLiveTestConfig.class, UmClientConfig.class, CommonTestConfig.class }, loader = AnnotationConfigContextLoader.class)
public class RoleBasicLiveTest {

    private final static String JSON = MediaType.APPLICATION_JSON.toString();

    @Autowired
    protected UmPaths paths;

    // tests

    @SuppressWarnings("unchecked")
    @Test
    public void whenAllRolesAreRetrieved_thenNotEmpty() {
        // When
        final Response response = givenAuthenticated().header(HttpHeaders.ACCEPT, JSON)
            .get(getUri());
        final List<Role> allRoles = response.as(List.class);

        // Then
        assertThat(allRoles, not(Matchers.<Role> empty()));
    }

    //

    @Test
    public final void givenResourceExists_whenResourceIsRetrieved_thenResourceIsCorrectlyRetrieved() {
        // Given
        final Role newResource = new Role(randomAlphabetic(8), Sets.<Privilege> newHashSet());
        final Response responseOfCreate = givenAuthenticated().contentType(JSON)
            .body(newResource)
            .post(getUri());
        final String uriOfResource = responseOfCreate.getHeader(HttpHeaders.LOCATION);

        // When
        final Response responseOfGet = givenAuthenticated().header(HttpHeaders.ACCEPT, JSON)
            .get(uriOfResource);
        final Role createdResource = responseOfGet.as(Role.class);

        // Then
        assertEquals(createdResource, newResource);
    }

    @Test
    public void givenInvalidResource_whenResourceIsUpdated_then400BadRequestIsReceived() {
        // Given
        final Role existingResource = create(new Role(randomAlphabetic(8), Sets.<Privilege> newHashSet()));
        existingResource.setName(null);

        // When
        final Response responseOfUpdate = givenAuthenticated().contentType(JSON)
            .body(existingResource)
            .put(getUri() + "/" + existingResource.getId());

        // Then
        assertThat(responseOfUpdate.getStatusCode(), is(400));
    }

    // UTIL

    public final Role create(final Role resource) {
        final Response responseOfCreate = givenAuthenticated().contentType(JSON)
            .body(resource)
            .post(getUri());
        final String uriOfResource = responseOfCreate.getHeader(HttpHeaders.LOCATION);

        final Response responseOfGet = givenAuthenticated().header(HttpHeaders.ACCEPT, JSON)
            .get(uriOfResource);
        return responseOfGet.as(Role.class);
    }

    private final RequestSpecification givenAuthenticated() {
        return RestAssured.given()
            .auth()
            .preemptive()
            .basic(Um.ADMIN_EMAIL, Um.ADMIN_PASS);
    }

    private final String getUri() {
        return paths.getRoleUri() + WebConstants.PATH_SEP;
    }

}
