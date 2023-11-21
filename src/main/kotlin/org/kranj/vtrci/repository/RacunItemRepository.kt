package org.kranj.vtrci.repository

import org.kranj.vtrci.model.RacunItem
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RacunItemRepository : JpaRepository<RacunItem, UUID> {
}