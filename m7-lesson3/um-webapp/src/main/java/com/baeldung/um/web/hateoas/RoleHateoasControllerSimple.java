package com.baeldung.um.web.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baeldung.um.persistence.model.Role;
import com.baeldung.um.service.IRoleService;

@Controller
//@RequestMapping(value = UmMappings.Hateoas.ROLES)
public class RoleHateoasControllerSimple {

    @Autowired
    private IRoleService service;
    
    //API -  find - one

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RoleResource findOne(@PathVariable("id") final Long id) {
        final Role entity =  service.findOne(id);
        return new RoleResource(entity);
    }
}
