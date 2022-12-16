package com.baeldung.um.web.controller;

import com.baeldung.um.persistence.model.Privilege
import com.baeldung.um.service.IPrivilegeService
import com.baeldung.um.util.UmMappings
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(UmMappings.PRIVILEGES)
class PrivilegeController(private val service: IPrivilegeService) {

    @GetMapping("/{id}")
    fun findOne(@PathVariable("id") id: Long): Privilege = findOneInternal(id);

    fun findOneInternal(id: Long): Privilege = getService().findOne(id);

    fun getService() = service;
}
