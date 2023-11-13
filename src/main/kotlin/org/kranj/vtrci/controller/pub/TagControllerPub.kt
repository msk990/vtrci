package org.kranj.vtrci.controller.pub

import org.kranj.vtrci.service.TagService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("api/v1/pub/tags")
@RestController
class TagControllerPub (val service: TagService) {
    @CrossOrigin
    @GetMapping
    fun getAllTags() = service.getAll()
}