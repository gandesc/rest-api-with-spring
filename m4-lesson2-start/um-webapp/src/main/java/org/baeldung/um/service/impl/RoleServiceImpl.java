package org.baeldung.um.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.baeldung.common.persistence.service.AbstractService;
import org.baeldung.um.persistence.dao.IRoleJpaDao;
import org.baeldung.um.persistence.model.Role;
import org.baeldung.um.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {

    @Autowired
    IRoleJpaDao dao;

    public RoleServiceImpl() {
        super();
    }

    // API

    // get/find

    @Override
    public Role findByName(final String name) {
        return getDao().findByName(name);
    }

    // create

    @Override
    public Role create(final Role entity) {
        return super.create(entity);
    }

    // Spring

    @Override
    protected final IRoleJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Role> getSpecificationExecutor() {
        return dao;
    }

}
