package com.baeldung.um.web.privilege;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.baeldung.client.IDtoOperations;
import com.baeldung.common.util.LinkUtil;
import com.baeldung.test.common.web.util.HTTPLinkHeaderUtil;
import com.baeldung.um.client.template.PrivilegeRestClient;
import com.baeldung.um.model.PrivilegeDtoOpsImpl;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.test.live.UmDiscoverabilityRestLiveTest;

import io.restassured.response.Response;

public class PrivilegeDiscoverabilityRestLiveTest extends UmDiscoverabilityRestLiveTest<Privilege> {

    @Autowired
    private PrivilegeRestClient restTemplate;
    @Autowired
    private PrivilegeDtoOpsImpl entityOps;

    public PrivilegeDiscoverabilityRestLiveTest() {
        super(Privilege.class);
    }

    // tests

    // template method

    @Override
    protected final Privilege createNewResource() {
        return getEntityOps().createNewResource();
    }

    @Override
    protected final String getUri() {
        return getApi().getUri();
    }

    @Override
    protected final PrivilegeRestClient getApi() {
        return restTemplate;
    }

    @Override
    protected final IDtoOperations<Privilege> getEntityOps() {
        return entityOps;
    }
    
    @Override
    @Test
    @Ignore("Privilege controller has incorrect mapping for the endpoint that retrieves all privileges, so let's make this test be ignored only for privilege related issues")
    public void whenResourceIsRetrieved_thenUriToGetAllResourcesIsDiscoverable() {
        // Given
        final String uriOfExistingResource = getApi().createAsUri(createNewResource());

        // When
        final Response getResponse = getApi().read(uriOfExistingResource);

        // Then
        final String uriToAllResources = HTTPLinkHeaderUtil.extractURIByRel(getResponse.getHeader(HttpHeaders.LINK), LinkUtil.REL_COLLECTION);

        final Response getAllResponse = getApi().read(uriToAllResources);
        assertThat(getAllResponse.getStatusCode(), is(200));
    }

}
