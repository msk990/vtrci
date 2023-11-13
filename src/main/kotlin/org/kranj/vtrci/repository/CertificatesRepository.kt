package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Certificate
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CertificatesRepository : JpaRepository<Certificate, UUID> {
}