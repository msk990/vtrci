package org.kranj.vtrci.repository

import org.kranj.vtrci.model.ProcessStep

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProcessStepRepository : JpaRepository<ProcessStep, UUID> {
}