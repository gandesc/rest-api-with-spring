package org.baeldung.common.persistence.service;

import org.springframework.data.domain.Page;

import org.baeldung.common.interfaces.IOperations;
import org.baeldung.common.persistence.model.IEntity;

public interface IRawService<T extends IEntity> extends IOperations<T> {

    Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder);

}
