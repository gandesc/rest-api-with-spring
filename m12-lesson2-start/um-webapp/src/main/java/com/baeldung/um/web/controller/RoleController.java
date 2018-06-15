package com.baeldung.um.web.controller;

import com.baeldung.common.web.controller.AbstractController;
import com.baeldung.common.web.controller.ISortingController;
import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.service.IRoleService;
import com.baeldung.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.baeldung.common.util.QueryConstants.*;

@RestController
@RequestMapping(UmMappings.ROLES)
public class RoleController extends AbstractController<Role> implements ISortingController<Role> {

    @Autowired private IRoleService service;

    public RoleController() {
        super(Role.class);
    }

    // API

    // find - all/paginated

    @Override
    @GetMapping(params = { PAGE, SIZE, SORT_BY })
    public List<Role> findAllPaginatedAndSorted(@RequestParam(PAGE) int page, @RequestParam(SIZE) int size, @RequestParam(SORT_BY) String sortBy, @RequestParam(SORT_ORDER) String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }

    @Override
    @GetMapping(params = { PAGE, SIZE })
    public List<Role> findAllPaginated(@RequestParam(PAGE) int page, @RequestParam(SIZE) int size) {
        return findPaginatedInternal(page, size);
    }

    @Override
    @GetMapping(params = { SORT_BY })
    public List<Role> findAllSorted(@RequestParam(value = SORT_BY) String sortBy, @RequestParam(value = SORT_ORDER) String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @GetMapping
    public List<Role> findAll(HttpServletRequest request) {
        return findAllInternal(request);
    }

    // find - one

    @GetMapping("/{id}")
    public Role findOne(@PathVariable("id") Long id) {
        return findOneInternal(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Role resource) {
        createInternal(resource);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody Role resource) {
        updateInternal(id, resource);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        deleteByIdInternal(id);
    }

    @Override
    protected IRoleService getService() {
        return service;
    }
}
