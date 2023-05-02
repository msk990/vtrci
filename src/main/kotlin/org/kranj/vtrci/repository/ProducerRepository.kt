package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Producer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProducerRepository : JpaRepository<Producer, UUID> {
}