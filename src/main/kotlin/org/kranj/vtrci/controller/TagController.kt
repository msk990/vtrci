package org.kranj.vtrci.controller

import org.kranj.vtrci.service.TagService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/tags")
@RestController
class TagController (val service: TagService) {
    @CrossOrigin
    @GetMapping
    fun getAllTags() = service.getAll()
}