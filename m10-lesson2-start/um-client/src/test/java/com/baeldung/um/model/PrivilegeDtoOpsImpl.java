package com.baeldung.um.model;

import com.baeldung.client.IDtoOperations;
import com.baeldung.um.persistence.model.Privilege;
import org.springframework.stereotype.Component;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public final class PrivilegeDtoOpsImpl implements IDtoOperations<Privilege> {

    public PrivilegeDtoOpsImpl() {
        super();
    }

    // template method

    @Override
    public final Privilege createNewResource() {
        return new Privilege(randomAlphabetic(8));
    }

    @Override
    public final void invalidate(final Privilege entity) {
        entity.setName(null);
    }

    @Override
    public void change(final Privilege resource) {
        resource.setName(randomAlphabetic(8));
    }

}
