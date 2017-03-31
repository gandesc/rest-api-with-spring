package com.baeldung.um.web.privilege;

import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.client.IDtoOperations;
import com.baeldung.um.client.template.PrivilegeRestClient;
import com.baeldung.um.model.PrivilegeDtoOpsImpl;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.test.live.UmSearchRestLiveTest;

public class PrivilegeSearchRestLiveTest extends UmSearchRestLiveTest<Privilege> {

    @Autowired
    private PrivilegeRestClient restTemplate;
    @Autowired
    private PrivilegeDtoOpsImpl entityOps;

    public PrivilegeSearchRestLiveTest() {
        super();
    }

    // tests

    // template

    @Override
    protected final PrivilegeRestClient getApi() {
        return restTemplate;
    }

    @Override
    protected final IDtoOperations<Privilege> getEntityOps() {
        return entityOps;
    }

}
