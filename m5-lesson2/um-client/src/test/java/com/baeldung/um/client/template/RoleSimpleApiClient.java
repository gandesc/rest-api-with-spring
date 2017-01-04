package com.baeldung.um.client.template;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.baeldung.common.spring.util.Profiles;
import com.baeldung.um.client.UmPaths;
import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.util.Um;
import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

@Component
@Profile(Profiles.CLIENT)
public final class RoleSimpleApiClient {

    private final static String JSON = MediaType.APPLICATION_JSON.toString();

    @Autowired
    protected UmPaths paths;

    // API

    // find - one

    public final Role findOne(final long id) {
        final Response findOneResponse = findOneAsResponse(id);
        Preconditions.checkState(findOneResponse.getStatusCode() == 200, "Find One operation didn't result in a 200 OK");
        return findOneResponse.as(Role.class);
    }

    public final Response findOneAsResponse(final long id) {
        return read(getUri() + "/" + id);
    }

    @SuppressWarnings("unchecked")
    public final List<Role> findAll() {
        return findAllAsResponse().as(List.class);
    }

    public Response findAllAsResponse() {
        return read(getUri());
    }

    public final Response createAsResponse(final Role role) {
        return givenAuthenticated().contentType(JSON).body(role).post(getUri());
    }

    public final Role create(final Role role) {
        final Response response = createAsResponse(role);
        final String locationOfNewResource = response.getHeader(HttpHeaders.LOCATION);
        return read(locationOfNewResource).as(Role.class);
    }

    public final Response updateAsResponse(final Role role) {
        return givenAuthenticated().contentType(JSON).body(role).put(getUri() + "/" + role.getId());
    }

    public final Role update(final Role role) {
        updateAsResponse(role);
        return read(getUri() + "/" + role.getId()).as(Role.class);
    }

    public final Response deleteAsResponse(final long id) {
        return givenAuthenticated().delete(getUri() + "/" + id);
    }

    // UTIL

    public final Response read(final String uri) {
        return givenAuthenticated().accept(JSON).get(uri);
    }

    public final String getUri() {
        return paths.getRoleUri();
    }

    public final RequestSpecification givenAuthenticated() {
        final Pair<String, String> credentials = getDefaultCredentials();
        return RestAssured.given().auth().preemptive().basic(credentials.getLeft(), credentials.getRight());
    }

    private final Pair<String, String> getDefaultCredentials() {
        return new ImmutablePair<String, String>(Um.ADMIN_EMAIL, Um.ADMIN_PASS);
    }

}
