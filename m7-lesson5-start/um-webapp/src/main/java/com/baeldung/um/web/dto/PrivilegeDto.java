package com.baeldung.um.web.dto;

import com.baeldung.common.interfaces.INameableDto;

public class PrivilegeDto implements INameableDto {
    private Long id;
    private String name;

    public PrivilegeDto() {
        super();
    }

    public PrivilegeDto(final String nameToSet) {
        super();
        name = nameToSet;
    }

    // API

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long idToSet) {
        id = idToSet;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String nameToSet) {
        name = nameToSet;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PrivilegeDto other = (PrivilegeDto) obj;
        if (name == null) {
            return other.name == null;
        } else
            return name.equals(other.name);
    }

    @Override
    public String toString() {
        return getName();
    }
}
