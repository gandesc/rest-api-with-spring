package com.baeldung.um.client.template;

import com.baeldung.common.spring.util.Profiles;
import com.baeldung.um.client.UmPaths;
import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.util.Um;
import com.google.common.base.Preconditions;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile(Profiles.CLIENT)
public final class RoleSimpleApiClient {

    private final static String JSON = MediaType.APPLICATION_JSON.toString();

    @Autowired
    protected UmPaths paths;

    // API

    public final Role findOne(final long id) {
        final Response response = findOneAsResponse(id);
        Preconditions.checkState(response.getStatusCode() == 200, "Find one operation didn't result in a 200 ok");

        return response.as(Role.class);
    }

    public final Response findOneAsResponse(final long id) {
        return read(getUri() + "/" + id);
    }

    public final List<Role> findAll() {
        return read(getUri()).as(List.class);
    }

    // UTIL

    private Response read(final String uri) {
        return givenAuthenticated().accept(JSON).get(uri);
    }

    public final String getUri() {
        return paths.getRoleUri();
    }

    public final RequestSpecification givenAuthenticated() {
        final Pair<String, String> credentials = getDefaultCredentials();
        return RestAssured.given()
                .auth()
                .preemptive()
                .basic(credentials.getLeft(), credentials.getRight());
    }

    private final Pair<String, String> getDefaultCredentials() {
        return new ImmutablePair<>(Um.ADMIN_EMAIL, Um.ADMIN_PASS);
    }

}
