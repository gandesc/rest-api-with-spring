package org.baeldung.um.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.stereotype.Component;

import org.baeldung.client.IDtoOperations;
import org.baeldung.um.client.FixtureResourceFactory;
import org.baeldung.um.persistence.model.User;

@Component
public final class UserDtoOpsImpl implements IDtoOperations<User> {

    public UserDtoOpsImpl() {
        super();
    }

    // API

    // template method

    @Override
    public final User createNewResource() {
        return FixtureResourceFactory.createNewUser();
    }

    @Override
    public final void invalidate(final User entity) {
        entity.setName(null);
    }

    @Override
    public final void change(final User resource) {
        resource.setName(randomAlphabetic(8));
    }

}
