package com.baeldung.um.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.common.web.controller.AbstractController;
import com.baeldung.common.web.controller.ISortingControllerAnnotated;
import com.baeldung.um.persistence.model.User;
import com.baeldung.um.service.IUserService;
import com.baeldung.um.util.UmMappings;

@RestController
@RequestMapping(UmMappings.USERS)
public class UserController extends AbstractController<User, User> implements ISortingControllerAnnotated<User> {

    @Autowired
    private IUserService service;

    public UserController() {
        super(User.class);
    }

    // API

    // find - all/paginated

    @Override

    public List<User> findAllPaginatedAndSorted(int page, int size, String sortBy, String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }

    @Override
    public List<User> findAllPaginated(int page, int size) {
        return findPaginatedInternal(page, size);
    }

    @Override
    public List<User> findAllSorted(String sortBy, String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    public List<User> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }

    @GetMapping("/{id}")
    public User findOne(@PathVariable("id") Long id) {
        return findOneInternal(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody User resource) {
        createInternal(resource);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody User resource) {
        updateInternal(id, resource);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected IUserService getService() {
        return service;
    }

}
