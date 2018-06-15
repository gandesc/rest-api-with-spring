package com.baeldung.um.web.dto

import com.baeldung.um.persistence.model.Privilege

data class PrivilegeDto(val id: Long, val name: String, val description: String?)

fun Privilege.toDto() = PrivilegeDto(id, name, description)



