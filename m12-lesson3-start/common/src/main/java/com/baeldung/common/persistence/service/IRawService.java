package com.baeldung.common.persistence.service;

import com.baeldung.common.interfaces.IOperations;
import com.baeldung.common.persistence.model.IEntity;
import org.springframework.data.domain.Page;

public interface IRawService<T extends IEntity> extends IOperations<T> {

    Page<T> findAllPaginatedAndSortedRaw(final int page, final int size, final String sortBy, final String sortOrder);

    Page<T> findAllPaginatedRaw(final int page, final int size);

}
