package com.baeldung.um.persistence.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baeldung.common.interfaces.IByNameApi;
import com.baeldung.um.persistence.model.Role;

public interface IRoleJpaDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role>, IByNameApi<Role> {

    Optional<Role> findRoleById(Long id);

    Stream<Role> findAllByName(String name);

}
