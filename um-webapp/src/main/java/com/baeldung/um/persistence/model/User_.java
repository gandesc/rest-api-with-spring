package com.baeldung.um.persistence.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

    public static volatile SingularAttribute<User, Long> id;
    public static volatile SetAttribute<User, Role> roles;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Boolean> locked;

}
