package com.baeldung.common.web.controller;

import static com.baeldung.common.util.QueryConstants.PAGE;
import static com.baeldung.common.util.QueryConstants.SIZE;
import static com.baeldung.common.util.QueryConstants.SORT_BY;
import static com.baeldung.common.util.QueryConstants.SORT_ORDER;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baeldung.common.interfaces.IDto;

public interface ISortingControllerAnnotated<D extends IDto> {

    @GetMapping(params = { PAGE, SIZE, SORT_BY })
    public List<D> findAllPaginatedAndSorted(@RequestParam(PAGE) final int page, @RequestParam(SIZE) final int size, @RequestParam(SORT_BY) final String sortBy, @RequestParam(SORT_ORDER) final String sortOrder);

    @GetMapping(params = { PAGE, SIZE })
    public List<D> findAllPaginated(@RequestParam(PAGE) final int page, @RequestParam(SIZE) final int size);

    @GetMapping(params = { SORT_BY })
    public List<D> findAllSorted(@RequestParam(SORT_BY) final String sortBy, @RequestParam(SORT_ORDER) final String sortOrder);

    @GetMapping
    public List<D> findAll(final HttpServletRequest request);

}
