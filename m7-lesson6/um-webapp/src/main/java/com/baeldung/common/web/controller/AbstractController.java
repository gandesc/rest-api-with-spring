package com.baeldung.common.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.baeldung.common.interfaces.IDto;
import com.baeldung.common.persistence.model.IEntity;
import com.baeldung.common.web.RestPreconditions;

public abstract class AbstractController<D extends IDto, E extends IEntity> extends AbstractReadOnlyController<D, E> {

    @Autowired
    public AbstractController(final Class<D> clazzToSet) {
        super(clazzToSet);
    }

    // save/create/persist

    protected final void createInternal(final D resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        getService().create(convertToEntity(resource));
    }

    // update

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
    protected final void updateInternal(final long id, final D resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

        getService().update(convertToEntity(resource));
    }

    // delete/remove

    protected final void deleteByIdInternal(final long id) {
        getService().delete(id);
    }

}
