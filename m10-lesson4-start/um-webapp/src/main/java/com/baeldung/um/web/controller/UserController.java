package com.baeldung.um.web.controller;

import java.util.List;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.baeldung.um.service.AsyncService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.baeldung.common.util.QueryConstants;
import com.baeldung.common.web.controller.AbstractController;
import com.baeldung.common.web.controller.ISortingController;
import com.baeldung.um.service.IUserService;
import com.baeldung.um.util.Um.Privileges;
import com.baeldung.um.util.UmMappings;
import com.baeldung.um.web.dto.UserDto;

@Controller
@RequestMapping(value = UmMappings.USERS)
public class UserController extends AbstractController<UserDto, UserDto> implements ISortingController<UserDto> {

    @Autowired
    private IUserService service;

    @Autowired
    private AsyncService asyncService;

    public UserController() {
        super(UserDto.class);
    }

    // API

    // find - all/paginated

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<UserDto> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
        @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder, uriBuilder, response);
    }

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<UserDto> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findPaginatedInternal(page, size, uriBuilder, response);
    }

    @Override
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<UserDto> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public List<UserDto> findAll(final HttpServletRequest request, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findAllInternal(request, uriBuilder, response);
    }

    // find - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public UserDto findOne(@PathVariable("id") final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        return findOneInternal(id, uriBuilder, response);
    }

    @RequestMapping(params = { "name" }, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_USER_READ)
    public UserDto findOneByName(@RequestParam(value = "name") final String name) {
        return getService().findByName(name);
    }

    // create

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final UserDto resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response) {
        createInternal(resource, uriBuilder, response);
    }

    @PostMapping("/callable")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Callable<UserDto> createCallable(@RequestBody final UserDto resource) {
        return () -> service.createSlow(resource);
    }

    @PostMapping("/deferred")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DeferredResult<UserDto> createDeferred(@RequestBody final UserDto resource) {
        DeferredResult<UserDto> result = new DeferredResult<>();
        asyncService.scheduleCreateUser(resource, result);

        return result;
    }

    @PostMapping("async")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void createAsync(@RequestBody final UserDto resource, HttpServletResponse response, UriComponentsBuilder uriBuilder) throws InterruptedException {
        asyncService.createUserAsync(resource);

        String location = uriBuilder.path("users")
                .queryParam("name", resource.getName())
                .build()
                .encode()
                .toString();

        response.setHeader("Location", location);
    }

    // update

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Secured(Privileges.CAN_USER_WRITE)
    public void update(@PathVariable("id") final Long id, @RequestBody @Valid final UserDto resource) {
        updateInternal(id, resource);
    }

    // delete

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Secured(Privileges.CAN_USER_WRITE)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected final IUserService getService() {
        return service;
    }

}
