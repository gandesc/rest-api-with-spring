package com.baeldung.um.web.hateoas;

import com.baeldung.um.persistence.model.Role;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class RoleResource extends RepresentationModel<RoleResource> {
    private Role role;

    public RoleResource(Role role) {
        this.role = role;

        this.add(linkTo(RoleHateoasControllerSimple.class).withRel("roles"));
        this.add(linkTo(methodOn(RoleHateoasControllerSimple.class, role).findOne(role.getId())).withSelfRel());
    }

    public Role getRole() {
        return role;
    }
}
