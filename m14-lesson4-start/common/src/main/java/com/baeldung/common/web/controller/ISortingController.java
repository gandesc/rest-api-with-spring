package com.baeldung.common.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.baeldung.common.interfaces.IDto;

public interface ISortingController<D extends IDto> {

    public List<D> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder);

    public List<D> findAllPaginated(final int page, final int size);

    public List<D> findAllSorted(final String sortBy, final String sortOrder);

    public List<D> findAll(final HttpServletRequest request);

}
