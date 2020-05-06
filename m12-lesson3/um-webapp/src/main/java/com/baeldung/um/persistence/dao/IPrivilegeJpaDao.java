package com.baeldung.um.persistence.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baeldung.common.interfaces.IByNameApi;
import com.baeldung.um.persistence.model.Privilege;

public interface IPrivilegeJpaDao extends JpaRepository<Privilege, Long>, JpaSpecificationExecutor<Privilege>, IByNameApi<Privilege> {

    Optional<Privilege> findPrivilegeById(Long id);

    Stream<Privilege> findAllByName(String name);
}
