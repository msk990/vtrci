package org.kranj.vtrci.service

import org.kranj.vtrci.model.MealTags
import org.kranj.vtrci.repository.MealTagRepository
import org.springframework.stereotype.Service

@Service
class MealTagService (val repository: MealTagRepository) {
fun getAll(): List<MealTags> =repository.findAll()
}