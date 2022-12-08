package com.baeldung.um.web.hateoas;

import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.service.IRoleService;
import com.baeldung.um.util.UmMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = UmMappings.Hateoas.ROLES)
public class RoleHateoasControllerSimple {

    @Autowired
    private IRoleService service;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public RoleResource findOne(@PathVariable("id") Long id) {
        Role entity = service.findOne(id);

        return new RoleResource(entity);
    }
}
