package com.baeldung.um.service.impl

import com.baeldung.common.persistence.service.AbstractService
import com.baeldung.um.persistence.dao.IPrivilegeJpaDao
import com.baeldung.um.persistence.model.Privilege
import com.baeldung.um.service.IPrivilegeService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PrivilegeServiceImpl(private val dao: IPrivilegeJpaDao) : AbstractService<Privilege>(), IPrivilegeService {

    // API

    // find

    override fun findByName(name: String): Privilege? = getDao().findByName(name)

    // Spring

    override fun getDao() = dao

    override fun getSpecificationExecutor() = dao
}
