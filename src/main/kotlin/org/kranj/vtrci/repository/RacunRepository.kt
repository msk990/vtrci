package org.kranj.vtrci.repository

import org.kranj.vtrci.model.Racun
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.util.*

interface RacunRepository : JpaRepository<Racun, UUID>{

    fun findByRacuner_IdEqualsOrderByRacunDateDesc(rac: UUID) : List<Racun>

    fun findByStatusIsAndRacuner_IdEqualsOrderByRacunDateDesc(stat:String, rac: UUID) : List<Racun>

    fun findByStatusIsAndRacuner_IdEqualsAndRacunee_IdEqualsAndRacunDateBetweenOrderByRacunDateDesc(stat:String, rac: UUID, racee:UUID, start: Instant, end:Instant) : List<Racun>

    fun findByRacuner_IdEqualsAndRacunDateBetweenOrderByRacunDateDesc(rac: UUID, start: Instant, end:Instant) : List<Racun>

}