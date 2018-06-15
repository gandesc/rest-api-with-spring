package com.baeldung.um.web.dto;

import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlRootElement;

import com.baeldung.um.persistence.model.Role;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@XmlRootElement
@Data
@EqualsAndHashCode(of = "name") @ToString(of = {"id", "name"})
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    private String password;

    private Boolean locked;

    private Set<@Email String> alternativeEmailAddresses;    

    private Optional<@Positive Long> age = Optional.empty();

    private Set<Role> roles;
}
