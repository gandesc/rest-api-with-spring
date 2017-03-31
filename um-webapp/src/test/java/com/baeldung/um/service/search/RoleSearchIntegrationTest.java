package com.baeldung.um.service.search;

import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.um.common.FixtureEntityFactory;
import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.service.IRoleService;

public class RoleSearchIntegrationTest extends SecSearchIntegrationTest<Role> {

    @Autowired
    private IRoleService roleService;

    // tests

    // template method

    @Override
    protected final IRoleService getApi() {
        return roleService;
    }

    @Override
    protected final Role createNewEntity() {
        return FixtureEntityFactory.createNewRole();
    }

}
