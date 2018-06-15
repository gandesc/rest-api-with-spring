package com.baeldung.um.persistence.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.xml.bind.annotation.XmlRootElement;

import com.baeldung.common.interfaces.INameableDto;
import com.baeldung.common.persistence.model.INameableEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@XmlRootElement
@Data
@EqualsAndHashCode(of = "name") @ToString(of = {"id", "name"})
public class User implements INameableEntity, INameableDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column( /* nullable = false */)
    private Boolean locked;

    @Column
    @ElementCollection
    private Set<@Email String> alternativeEmailAddresses;

    /*@Temporal(value = TemporalType.DATE)
    @Column
    @PastOrPresent    
    private Date dateOfBirth;*/

    @Column
    private Long age;

    // @formatter:off
    @ManyToMany( /* cascade = { CascadeType.REMOVE }, */fetch = FetchType.EAGER)
    @JoinTable(joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
    private Set<Role> roles;
    // @formatter:on

    public User() {
        super();

        locked = false;
    }

    public User(final String name, final String email, final String password, final Set<Role> roles) {
        super();

        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(final String nameToSet, final String passwordToSet, final Set<Role> rolesToSet) {
        super();

        name = nameToSet;
        password = passwordToSet;
        roles = rolesToSet;
    }

    public User(final User user) {
        super();

        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        roles = user.getRoles();
    }
}
