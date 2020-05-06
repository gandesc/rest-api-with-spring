package com.baeldung.um.persistence.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.baeldung.common.interfaces.IByNameApi;
import com.baeldung.um.persistence.model.User;

public interface IUserJpaDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, IByNameApi<User> {

    Optional<User> findUserById(Long id);

    Stream<User> findAllByName(String name);
}