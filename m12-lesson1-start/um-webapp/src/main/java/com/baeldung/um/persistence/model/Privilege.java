package com.baeldung.um.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import com.baeldung.common.interfaces.INameableDto;
import com.baeldung.common.persistence.model.INameableEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@XmlRootElement
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString(exclude = "id")
public class Privilege implements INameableEntity, INameableDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRIV_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = false, nullable = true)
    private String description;

    public Privilege(final String nameToSet) {
        super();
        name = nameToSet;
    }
}
