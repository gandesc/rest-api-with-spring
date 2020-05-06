package com.baeldung.um.web.hateoas;

import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.baeldung.um.persistence.model.Role;

public class RoleResource extends RepresentationModel {

    private final Role role;

    public RoleResource(final Role role) {
        this.role = role;

        this.add(linkTo(RoleHateoasController.class).withRel("roles"));
        this.add(linkTo(methodOn(RoleHateoasController.class, role).findOne(role.getId())).withSelfRel());
    }

    //
    public Role getRole() {
        return role;
    }

}
