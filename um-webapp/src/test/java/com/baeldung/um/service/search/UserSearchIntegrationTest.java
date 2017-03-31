package com.baeldung.um.service.search;

import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.um.common.FixtureEntityFactory;
import com.baeldung.um.persistence.model.User;
import com.baeldung.um.service.IUserService;

public class UserSearchIntegrationTest extends SecSearchIntegrationTest<User> {

    @Autowired
    private IUserService userService;

    // tests

    // template method

    @Override
    protected final IUserService getApi() {
        return userService;
    }

    @Override
    protected final User createNewEntity() {
        return FixtureEntityFactory.createNewUser();
    }

}
