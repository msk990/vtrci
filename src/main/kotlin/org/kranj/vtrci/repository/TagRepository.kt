package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TagRepository : JpaRepository<Tag, UUID>{
}