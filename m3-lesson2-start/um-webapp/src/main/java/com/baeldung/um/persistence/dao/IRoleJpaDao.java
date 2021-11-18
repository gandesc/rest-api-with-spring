package com.baeldung.um.persistence.dao;

import com.baeldung.common.interfaces.IByNameApi;
import com.baeldung.um.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IRoleJpaDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>, IByNameApi<Role> {
    //
}
