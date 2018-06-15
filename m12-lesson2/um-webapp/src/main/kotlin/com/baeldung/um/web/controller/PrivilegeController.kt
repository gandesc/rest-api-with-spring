package com.baeldung.um.web.controller


import com.baeldung.common.util.QueryConstants.PAGE
import com.baeldung.common.util.QueryConstants.SIZE
import com.baeldung.common.util.QueryConstants.SORT_BY
import com.baeldung.common.util.QueryConstants.SORT_ORDER
import com.baeldung.common.web.controller.AbstractController
import com.baeldung.common.web.controller.ISortingController
import com.baeldung.um.persistence.model.Privilege
import com.baeldung.um.service.IPrivilegeService
import com.baeldung.um.util.UmMappings
import com.baeldung.um.web.dto.PrivilegeDto
import com.baeldung.um.web.dto.toDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping(UmMappings.PRIVILEGES)
class PrivilegeController(private val service: IPrivilegeService)
    : AbstractController<Privilege>(Privilege::class.java), ISortingController<Privilege> {

    // API

    // find - all/paginated

    @GetMapping(params = [PAGE, SIZE, SORT_BY])
    override fun findAllPaginatedAndSorted(
            @RequestParam(PAGE) page: Int,
            @RequestParam(SIZE) size: Int,
            @RequestParam(SORT_BY) sortBy: String,
            @RequestParam(SORT_ORDER) sortOrder: String): List<Privilege> =
            findPaginatedAndSortedInternal(page, size, sortBy, sortOrder)

    @GetMapping(params = [PAGE, SIZE])
    override fun findAllPaginated(
            @RequestParam(PAGE) page: Int,
            @RequestParam(SIZE) size: Int): List<Privilege> = findPaginatedInternal(page, size)

    @GetMapping(params = [SORT_BY])
    override fun findAllSorted(
            @RequestParam(SORT_BY) sortBy: String,
            @RequestParam(SORT_ORDER) sortOrder: String): List<Privilege> = findAllSortedInternal(sortBy, sortOrder)

    @GetMapping
    override fun findAll(request: HttpServletRequest): List<Privilege> = findAllInternal(request)

    // find - one

    @GetMapping("/{id}")
    fun findOne(
            @PathVariable("id") id: Long): PrivilegeDto = findOneInternal(id).toDto()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(
            @RequestBody resource: Privilege) {
        createInternal(resource)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun update(
            @PathVariable("id") id: Long,
            @RequestBody resource: Privilege) {
        updateInternal(id, resource)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable("id") id: Long) {
        deleteByIdInternal(id)
    }

    // Spring

    override fun getService() = service
}
