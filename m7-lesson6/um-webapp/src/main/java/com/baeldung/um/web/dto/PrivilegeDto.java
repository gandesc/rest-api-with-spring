package com.baeldung.um.web.dto;

import java.util.Set;

import com.baeldung.common.interfaces.INameableDto;
import com.baeldung.um.persistence.model.Privilege;
import com.baeldung.um.persistence.model.Role;

public class PrivilegeDto implements INameableDto {

    private Long id;

    private String name;

    private Set<Role> roles;

    public PrivilegeDto() {

    }

    public PrivilegeDto(Privilege privilege) {
        this.id = privilege.getId();
        this.name = privilege.getName();
        this.roles = privilege.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrivilegeDto other = (PrivilegeDto) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

}
