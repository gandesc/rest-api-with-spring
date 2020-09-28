package com.baeldung.common.web.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baeldung.common.interfaces.IDto;
import com.baeldung.common.persistence.model.IEntity;
import com.baeldung.common.persistence.service.IRawService;
import com.baeldung.common.web.RestPreconditions;
import com.baeldung.common.web.exception.MyResourceNotFoundException;
import com.baeldung.um.persistence.model.Privilege;

public abstract class AbstractReadOnlyController<D extends IDto, E extends IEntity> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Class<D> clazz;

    public AbstractReadOnlyController(final Class<D> clazzToSet) {
        super();

        Objects.requireNonNull(clazzToSet);
        clazz = clazzToSet;
    }

    // find - one

    protected final D findOneInternal(final Long id) {
        return this.convertToDto(RestPreconditions.checkNotNull(getService().findOne(id)));
    }

    // find - all

    protected final List<D> findAllInternal(final HttpServletRequest request) {
        if (request.getParameterNames()
            .hasMoreElements()) {
            throw new MyResourceNotFoundException();
        }

        return getService().findAll()
            .stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    protected final List<D> findPaginatedAndSortedInternal(final int page, final int size, final String sortBy, final String sortOrder) {
        final Page<E> entities = getService().findAllPaginatedAndSortedRaw(page, size, sortBy, sortOrder);
        List<D> dtos = entities.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());

        if (page > entities.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }

        return dtos;
    }

    protected final List<D> findPaginatedInternal(final int page, final int size) {
        final Page<E> entities = getService().findAllPaginatedRaw(page, size);
        List<D> dtos = (List<D>) entities.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());

        if (page > entities.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }

        return dtos;
    }

    protected final List<D> findAllSortedInternal(final String sortBy, final String sortOrder) {
        final List<E> entities = getService().findAllSorted(sortBy, sortOrder);
        return (List<D>) entities.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    // count

    protected final long countInternal() {
        // InvalidDataAccessApiUsageException dataEx - ResourceNotFoundException
        return getService().count();
    }

    // generic REST operations

    // count

    /**
     * Counts all {@link Privilege} resources in the system
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/count")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public long count() {
        return countInternal();
    }

    // util

    protected abstract D convertToDto(E entity);

    protected abstract E convertToEntity(D dto);

    // template method

    protected abstract IRawService<E> getService();

}
