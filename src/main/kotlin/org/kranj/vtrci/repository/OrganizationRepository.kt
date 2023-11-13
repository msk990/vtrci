package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Organization
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface OrganizationRepository : JpaRepository<Organization, UUID> {
}