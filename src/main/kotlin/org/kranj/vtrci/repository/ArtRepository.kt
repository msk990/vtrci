package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Art
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ArtRepository : JpaRepository<Art, UUID> {
}