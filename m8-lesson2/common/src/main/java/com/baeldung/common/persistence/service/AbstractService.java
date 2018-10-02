package com.baeldung.common.persistence.service;

import com.baeldung.common.interfaces.IWithName;

public abstract class AbstractService<T extends IWithName> extends AbstractRawService<T> implements IService<T> {

    public AbstractService(final Class<T> clazzToSet) {
        super(clazzToSet);
    }

    // API

    // find - one

}
