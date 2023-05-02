package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Identity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface IdentityRepository : JpaRepository<Identity, UUID> {
}