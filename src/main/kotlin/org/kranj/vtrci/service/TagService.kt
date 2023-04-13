package org.kranj.vtrci.service

import org.kranj.vtrci.model.Tag
import org.kranj.vtrci.repository.TagRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TagService (val repository:TagRepository) {
    fun getAll(): List<Tag> = repository.findAll()
}