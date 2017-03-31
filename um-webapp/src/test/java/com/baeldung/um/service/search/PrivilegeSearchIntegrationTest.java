package com.baeldung.um.service.search;

import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.um.common.FixtureEntityFactory;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.service.IPrivilegeService;

public class PrivilegeSearchIntegrationTest extends SecSearchIntegrationTest<Privilege> {

    @Autowired
    private IPrivilegeService privilegeService;

    // tests

    // template method

    @Override
    protected final IPrivilegeService getApi() {
        return privilegeService;
    }

    @Override
    protected final Privilege createNewEntity() {
        return FixtureEntityFactory.createNewPrivilege();
    }

}
