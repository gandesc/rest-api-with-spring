package com.baeldung.um.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.common.util.QueryConstants;
import com.baeldung.common.web.controller.AbstractController;
import com.baeldung.common.web.controller.ISortingController;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.service.IPrivilegeService;
import com.baeldung.um.util.UmMappings;
import com.baeldung.um.web.dto.PrivilegeDto;

@RestController
@RequestMapping(UmMappings.PRIVILEGES)
public class PrivilegeController extends AbstractController<PrivilegeDto, Privilege> implements ISortingController<PrivilegeDto> {

    @Autowired
    private IPrivilegeService service;

    @Autowired
    protected ModelMapper modelmapper;

    public PrivilegeController() {
        super(PrivilegeDto.class);
    }

    // API

    // find - all/paginated

    @Override
    @GetMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY })
    public List<PrivilegeDto> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
        @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }

    @Override
    @GetMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE })
    public List<PrivilegeDto> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        return findPaginatedInternal(page, size);
    }

    @Override
    @GetMapping(params = { QueryConstants.SORT_BY })
    public List<PrivilegeDto> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @GetMapping
    public List<PrivilegeDto> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }

    // find - one

    @GetMapping("/{id}")
    public PrivilegeDto findOne(@PathVariable("id") final Long id) {
        return findOneInternal(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final PrivilegeDto resource) {
        createInternal(resource);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") final Long id, @RequestBody final PrivilegeDto resource) {
        updateInternal(id, resource);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // util

    @Override
    protected PrivilegeDto convertToDto(Privilege entity) {
        return this.modelmapper.map(entity, PrivilegeDto.class);
    }

    @Override
    protected Privilege convertToEntity(PrivilegeDto dto) {
        return this.modelmapper.map(dto, Privilege.class);
    }

    // Spring

    @Override
    protected final IPrivilegeService getService() {
        return service;
    }

}
