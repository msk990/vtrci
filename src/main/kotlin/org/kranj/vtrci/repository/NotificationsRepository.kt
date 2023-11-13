package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Notification
import org.kranj.vtrci.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface NotificationsRepository : JpaRepository<Notification, UUID> {

    fun findByOrganizationIdEqualsOrderByIssuedAsc(orgId: UUID?): List<Notification>

    fun countByOrganizationIdEqualsAndStatusIs(orgId: UUID, status: String ): Long

}