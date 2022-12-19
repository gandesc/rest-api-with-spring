package com.baeldung.um.persistence.model;

import com.baeldung.common.interfaces.INameableDto;
import com.baeldung.common.persistence.model.INameableEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@ToString(of = "name")
public class Privilege implements INameableEntity, INameableDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRIV_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column()
    private String description;

    @JsonbTransient
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Privilege() {
        super();
    }

    public Privilege(final String nameToSet) {
        super();
        name = nameToSet;
    }
}
