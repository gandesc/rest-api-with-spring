package com.baeldung.um.web.controller;

import com.baeldung.common.util.QueryConstants;
import com.baeldung.common.web.controller.AbstractController;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.service.IPrivilegeService;
import com.baeldung.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(UmMappings.PRIVILEGESx)
public class PrivilegeVxController extends AbstractController<Privilege, Privilege> {

    @Autowired
    private IPrivilegeService service;

    public PrivilegeVxController() {
        super(Privilege.class);
    }

    // API
    // find - one

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
