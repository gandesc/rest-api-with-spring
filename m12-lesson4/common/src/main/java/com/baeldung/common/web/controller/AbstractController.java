package com.baeldung.common.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import com.baeldung.common.interfaces.IDto;
import com.baeldung.common.persistence.model.INameableEntity;
import com.baeldung.common.web.RestPreconditions;

public abstract class AbstractController<D extends IDto, E extends INameableEntity> extends AbstractReadOnlyController<D, E> {

    @Autowired
    public AbstractController(final Class<D> clazzToSet) {
        super(clazzToSet);
    }

    // save/create/persist

    protected final void createInternal(final E resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
    }

    // update

    /**
     * - note: the operation is IDEMPOTENT <br/>
     */
    protected final void updateInternal(final long id, final E resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

        getService().update(resource);
    }

    // delete/remove

    protected final void deleteByIdInternal(final long id) {
        // InvalidDataAccessApiUsageException - ResourceNotFoundException
        // IllegalStateException - ResourceNotFoundException
        // DataAccessException - ignored
        getService().delete(id);
    }

}
