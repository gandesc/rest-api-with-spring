package com.baeldung.um.client.template;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.baeldung.client.marshall.IMarshaller;
import com.baeldung.common.spring.util.Profiles;
import com.baeldung.common.util.QueryConstants;
import com.baeldung.common.web.WebConstants;
import com.baeldung.um.client.UmPaths;
import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.util.Um;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

@Component
@Profile(Profiles.CLIENT)
public final class RoleSimpleApiClient {
    
    private final static String JSON = MediaType.APPLICATION_JSON.toString();

    @Autowired
    protected UmPaths paths;

    @Autowired
    private IMarshaller marshaller;

    private final Class<Role> clazz = Role.class;

    // API

    // find - one

    public final Role findOne(final long id) {
        final String uriOfResource = getUri() + WebConstants.PATH_SEP + id;
        return findOneByUri(uriOfResource);
    }

    public final Response findOneAsResponse(final long id) {
        final String uriOfResource = getUri() + WebConstants.PATH_SEP + id;
        return findByUriAsResponse(uriOfResource);
    }

    private final Role findOneByUri(final String uriOfResource) {
        String resourceAsMime = findOneByUriAsString(uriOfResource);       
        return marshaller.decode(resourceAsMime, clazz);
    }
    
    private final String findOneByUriAsString(final String uriOfResource) {
        final Response response = read(uriOfResource);
        Preconditions.checkState(response.getStatusCode() == 200);

        return response.asString();
    }
    
    public final Response findByUriAsResponse(final String uriOfResource){
        return read(uriOfResource);
        
    }

    // find - all
    
    public final List<Role> findAll(){
        return findAll(getUri());
    }

    public final List<Role> findAll(final String uri) {
        final Response allAsResponse = read(uri);
        final List<Role> listOfResources = marshaller.<Role> decodeList(allAsResponse.getBody().asString(), clazz);
        if (listOfResources == null) {
            return Lists.newArrayList();
        }
        return listOfResources;
    }
    
    public final Response finaAllAsResponse(){
        return findByUriAsResponse(getUri());
    }

    // find - all (sorted, paginated)
    
    public final List<Role> findAllSorted(final String sortBy, final String sortOrder){
        final Response findAllResponse = findByUriAsResponse(getUri() + QueryConstants.Q_SORT_BY + sortBy + QueryConstants.S_ORDER + sortOrder);
        return marshaller.<Role>decodeList(findAllResponse.getBody().asString(),clazz);
    }

    public final List<Role> findAllPaginated(final int page, final int size){
        final Response allPaginatedAsResponse = findAllPaginatedAsResponse(page, size);
        return marshaller.<Role>decodeList(allPaginatedAsResponse.getBody().asString(),clazz);
    }
    
    public final List<Role> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder){
        final Response allPaginatedAndSortedAsResponse = findAllPaginatedAndSortedAsResponse(page, size, sortBy, sortOrder );
        return marshaller.<Role>decodeList(allPaginatedAndSortedAsResponse.getBody().asString(),clazz);
    }
    
    
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

    //count
    
    public final Response countAsResponse() {
        return read(getUri() + "/count");
    }
    
    // create

    public final Role create(final Role resource) {
               
        final String uriForResourceCreation = createAsUri(resource);
        final String resourceAsMime = findOneByUriAsString(uriForResourceCreation);
        return marshaller.decode(resourceAsMime, clazz);
    }

    public final String createAsUri(final Role resource) {
        final Response response = createAsResponse(resource);
        Preconditions.checkState(response.getStatusCode() == 201, "create operation: " + response.getStatusCode());

        final String locationOfCreatedResource = response.getHeader(HttpHeaders.LOCATION);
        Preconditions.checkNotNull(locationOfCreatedResource);
        return locationOfCreatedResource;
    }

    
    public final Response createAsResponse(final Role resource) {
        Preconditions.checkNotNull(resource);
        final RequestSpecification givenAuthenticated = givenAuthenticated();

        return givenAuthenticated.contentType(JSON).body(resource).post(getUri());
    }

   /* public final Response createAsResponse(final String resourceAsString) {
        Preconditions.checkNotNull(resourceAsString);
        final RequestSpecification givenAuthenticated = givenAuthenticated();

        return givenAuthenticated.contentType(JSON).body(resourceAsString).post(getUri());
    }*/

    // update

    public final void update(final Role resource) {
        final Response updateResponse = updateAsResponse(resource);
        Preconditions.checkState(updateResponse.getStatusCode() == 200, "Update Operation: " + updateResponse.getStatusCode());
    }

    public final Response updateAsResponse(final Role resource) {
        Preconditions.checkNotNull(resource);

        return givenAuthenticated().contentType(JSON).body(resource).put(getUri() + "/" + resource.getId());
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

    public final String getUri() {
        return paths.getRoleUri();
    }

    public final RequestSpecification givenAuthenticated() {
        final Pair<String, String> credentials = getDefaultCredentials();
        return RestAssured.given().auth().preemptive().basic(credentials.getLeft(), credentials.getRight());
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
        return new ImmutablePair<String, String>(Um.ADMIN_EMAIL, Um.ADMIN_PASS);
    }

    public final IMarshaller getMarshaller() {
        return marshaller;
    }
}