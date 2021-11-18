package com.baeldung.um.model;

import com.baeldung.client.IDtoOperations;
import com.baeldung.um.client.FixtureResourceFactory;
import com.baeldung.um.persistence.model.Principal;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public final class PrincipalDtoOpsImpl implements IDtoOperations<Principal> {

    public PrincipalDtoOpsImpl() {
        super();
    }

    // API

    // template method

    @Override
    public final Principal createNewResource() {
        return FixtureResourceFactory.createNewPrincipal();
    }

    @Override
    public final void invalidate(final Principal entity) {
        entity.setName(null);
    }

    @Override
    public final void change(final Principal resource) {
        resource.setName(randomAlphabetic(8));
    }

}
