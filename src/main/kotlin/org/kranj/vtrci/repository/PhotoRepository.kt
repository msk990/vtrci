package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Photo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PhotoRepository : JpaRepository<Photo, UUID> {
}