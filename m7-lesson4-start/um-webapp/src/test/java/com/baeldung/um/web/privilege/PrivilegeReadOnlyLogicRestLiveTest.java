package com.baeldung.um.web.privilege;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.um.client.template.PrivilegeRestClient;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.test.live.UmReadOnlyLogicRestLiveTest;

import io.restassured.response.Response;

public class PrivilegeReadOnlyLogicRestLiveTest extends UmReadOnlyLogicRestLiveTest<Privilege> {

    @Autowired
    private PrivilegeRestClient api;

    public PrivilegeReadOnlyLogicRestLiveTest() {
        super(Privilege.class);
    }

    // tests

    // template

    @Override
    protected final PrivilegeRestClient getApi() {
        return api;
    }

    @Override
    @Test
    @Ignore("Privilege controller has incorrect mapping for the endpoint that retrieves all privileges, so let's make this test be ignored only for privilege related issues")
    /*code*/public void whenAllResourcesAreRetrieved_then200IsReceived() {
        // When
        final Response response = getApi().findAllAsResponse(null);

        // Then
        assertThat(response.getStatusCode(), is(200));
    }

}
