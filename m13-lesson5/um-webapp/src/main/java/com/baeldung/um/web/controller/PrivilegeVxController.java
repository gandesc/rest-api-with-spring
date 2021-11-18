package com.baeldung.um.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baeldung.common.web.controller.AbstractController;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.service.IPrivilegeService;
import com.baeldung.um.util.UmMappings;

@RestController
@RequestMapping(UmMappings.PRIVILEGESX)
public class PrivilegeVxController extends AbstractController<Privilege, Privilege> {

    @Autowired
    private IPrivilegeService service;

    public PrivilegeVxController() {
        super(Privilege.class);
    }

    // API - find - one

    @GetMapping("/{id}")
    public Privilege findOne(@PathVariable("id") final Long id) {
        return findOneInternal(id);
    }

    // Spring

    @Override
    protected final IPrivilegeService getService() {
        return service;
    }
}
