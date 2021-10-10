package com.baeldung.um.web.dto;

import com.baeldung.common.interfaces.INameableDto;
import com.baeldung.um.persistence.model.Privilege;

public class PrivilegeDto implements INameableDto {

    private Long id;

    private String name;

    public PrivilegeDto() {

    }

    public PrivilegeDto(Privilege privilege) {
        this.id = privilege.getId();
        this.name = privilege.getName();
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
