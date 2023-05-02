package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Art
import org.kranj.vtrci.model.FrontArt
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FrontArtRepository : JpaRepository<FrontArt, UUID> {
   // fun findByType(type:Int, pageable:Pageable): Page<Art>
}