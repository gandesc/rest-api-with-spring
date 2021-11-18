package com.baeldung.um.client.template;

import com.baeldung.client.marshall.IMarshaller;
import com.baeldung.common.interfaces.IDto;
import com.baeldung.common.spring.util.Profiles;
import com.baeldung.common.util.QueryConstants;
import com.baeldung.common.web.WebConstants;
import com.baeldung.um.client.UmPaths;
import com.baeldung.um.util.Um;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile(Profiles.CLIENT)
public abstract class GenericSimpleApiClient<T extends IDto> {

    private final static String JSON = MediaType.APPLICATION_JSON.toString();

    @Autowired
    protected UmPaths paths;

    @Autowired
    protected IMarshaller marshaller;

    private final Class<T> clazz;

    public GenericSimpleApiClient(final Class<T> clazz) {
        this.clazz = clazz;
    }

    // API

    // find - one

    public final T findOne(final long id) {
        final String uriOfResource = getUri() + WebConstants.PATH_SEP + id;
        return findOneByUri(uriOfResource);
    }

    public final Response findOneAsResponse(final long id) {
        final String uriOfResource = getUri() + WebConstants.PATH_SEP + id;
        return read(uriOfResource);
    }

    private final T findOneByUri(final String uriOfResource) {
        final Response response = read(uriOfResource);
        Preconditions.checkState(response.getStatusCode() == 200);

        return marshaller.decode(response.asString(), clazz);
    }

    // find - all

    public final List<T> findAll() {
        final Response allAsResponse = read(getUri());
        final List<T> listOfResources = marshaller.<T> decodeList(allAsResponse.getBody()
            .asString(), clazz);
        if (listOfResources == null) {
            return Lists.newArrayList();
        }
        return listOfResources;
    }

    // find - all (sorted, paginated)

    public final Response findAllPaginatedAndSortedAsResponse(final int page, final int size, final String sortBy, final String sortOrder) {
        final StringBuilder uri = new StringBuilder(getUri());
        uri.append(QueryConstants.QUESTIONMARK);
        uri.append("page=");
        uri.append(page);
        uri.append(QueryConstants.SEPARATOR_AMPER);
        uri.append("size=");
        uri.append(size);
        Preconditions.checkState(!(sortBy == null && sortOrder != null));
        if (sortBy != null) {
            uri.append(QueryConstants.SEPARATOR_AMPER);
            uri.append(QueryConstants.SORT_BY + "=");
            uri.append(sortBy);
        }
        if (sortOrder != null) {
            uri.append(QueryConstants.SEPARATOR_AMPER);
            uri.append(QueryConstants.SORT_ORDER + "=");
            uri.append(sortOrder);
        }

        return read(uri.toString());
    }

    public final Response findAllSortedAsResponse(final String sortBy, final String sortOrder) {
        final StringBuilder uri = new StringBuilder(getUri());
        uri.append(QueryConstants.QUESTIONMARK);
        Preconditions.checkState(!(sortBy == null && sortOrder != null));
        if (sortBy != null) {
            uri.append(QueryConstants.SORT_BY + "=");
            uri.append(sortBy);
        }
        if (sortOrder != null) {
            uri.append(QueryConstants.SEPARATOR_AMPER);
            uri.append(QueryConstants.SORT_ORDER + "=");
            uri.append(sortOrder);
        }

        return read(uri.toString());
    }

    public final Response findAllPaginatedAsResponse(final int page, final int size) {
        final StringBuilder uri = new StringBuilder(getUri());
        uri.append(QueryConstants.QUESTIONMARK);
        uri.append("page=");
        uri.append(page);
        uri.append(QueryConstants.SEPARATOR_AMPER);
        uri.append("size=");
        uri.append(size);
        return read(uri.toString());
    }

    // create

    public final T create(final T resource) {
        final Response response = createAsResponse(resource);
        Preconditions.checkState(response.getStatusCode() == 201, "create operation: " + response.getStatusCode());

        final String locationOfCreatedResource = response.getHeader(HttpHeaders.LOCATION);
        Preconditions.checkNotNull(locationOfCreatedResource);
        return findOneByUri(locationOfCreatedResource);
    }

    public final Response createAsResponse(final T resource) {
        Preconditions.checkNotNull(resource);
        final RequestSpecification givenAuthenticated = givenAuthenticated();

        return givenAuthenticated.contentType(JSON)
            .body(resource)
            .post(getUri());
    }

    // update

    public final void update(final T resource) {
        final Response updateResponse = updateAsResponse(resource);
        Preconditions.checkState(updateResponse.getStatusCode() == 200, "Update Operation: " + updateResponse.getStatusCode());
    }

    public final Response updateAsResponse(final T resource) {
        Preconditions.checkNotNull(resource);

        return givenAuthenticated().contentType(JSON)
            .body(resource)
            .put(getUri() + "/" + resource.getId());
    }

    // delete

    public final void delete(final long id) {
        final Response deleteResponse = deleteAsResponse(id);
        Preconditions.checkState(deleteResponse.getStatusCode() == 204);
    }

    public final Response deleteAsResponse(final long id) {
        return givenAuthenticated().delete(getUri() + WebConstants.PATH_SEP + id);
    }

    // API - other

    public abstract String getUri();

    public final RequestSpecification givenAuthenticated() {
        final Pair<String, String> credentials = getDefaultCredentials();
        return RestAssured.given()
            .auth()
            .preemptive()
            .basic(credentials.getLeft(), credentials.getRight());
    }

    public final Response read(final String uriOfResource) {
        return readRequest().get(uriOfResource);
    }

    // UTIL

    private final RequestSpecification readRequest() {
        return readRequest(givenAuthenticated());
    }

    private final RequestSpecification readRequest(final RequestSpecification req) {
        return req.header(HttpHeaders.ACCEPT, JSON);
    }

    private final Pair<String, String> getDefaultCredentials() {
        return new ImmutablePair<>(Um.ADMIN_EMAIL, Um.ADMIN_PASS);
    }

}
