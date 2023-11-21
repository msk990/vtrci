package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.DeliveryDto
import org.kranj.vtrci.dtos.RacunDelivery
import org.kranj.vtrci.dtos.RacunDto
import org.kranj.vtrci.model.Delivery
import org.kranj.vtrci.model.InventoryItem
import org.kranj.vtrci.model.Racun
import org.kranj.vtrci.model.RacunItem
import org.kranj.vtrci.repository.*
import org.kranj.vtrci.transformer.toDeliveryBuyerDto
import org.kranj.vtrci.transformer.toRacunDelivery
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class RacunService(
    val repository: RacunRepository,
    val productRepository: ProductRepository,
    val userService: UserService,
    val organizationRepository: OrganizationRepository,
    val inventoryRepository: InventoryItemRepository
) {

    fun getById(id: UUID): Racun = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun getMyRacuns(): List<Racun>? {
        val user = userService.getOrgFromUser()
        val orgId = user?.id
        return orgId?.let { repository.findByRacuner_IdEqualsOrderByRacunDateDesc(it) }
    }

    fun getMyRacunsByStatus(status: String): List<Racun>? {
        val user = userService.getOrgFromUser()
        val orgId = user?.id
        return orgId?.let { repository.findByStatusIsAndRacuner_IdEqualsOrderByRacunDateDesc(status, it) }
    }

    fun getRacunsByStatusAndProducerId(status:String, producerId:UUID):List<Racun>? {
        val user = userService.getOrgFromUser()
        val orgId = user?.id

        val start = Instant.now().minus(2, ChronoUnit.DAYS)
        val end = Instant.now().plus(2, ChronoUnit.DAYS)
        return orgId?.let { repository.findByStatusIsAndRacuner_IdEqualsAndRacunee_IdEqualsAndRacunDateBetweenOrderByRacunDateDesc(status, it, producerId, start, end) }
    }

    fun getMyBuyerRacunsBetween(start: Instant, end: Instant) : List<RacunDelivery>? {
        val user = userService.getOrgFromUser()
        val orgId = user?.id
        return orgId?.let {
            repository.findByRacuner_IdEqualsAndRacunDateBetweenOrderByRacunDateDesc(it,start, end).map(
                Racun::toRacunDelivery)
        }
    }

    fun create(racun: RacunDto) : Racun? {
        var itms: MutableList<RacunItem> = mutableListOf()
        val user = userService.getOrgFromUser()
        racun.items.forEach {
           val product = productRepository.getReferenceById(it.productId)
            val itm = RacunItem(
                UUID.randomUUID(),
                product = product,
                quantity = it.quantity,
                unit = it.unit
            )
            itms.add(itm)
        }
        if (user != null) {
            val newRacun = Racun(
                UUID.randomUUID(),
                itms.toList(),
                racun.racunDate,
                user,
                organizationRepository.getReferenceById(racun.racunee),
                "ordered"
            )

            return repository.save(newRacun)
        } else
        { throw ResponseStatusException(HttpStatus.NOT_FOUND)}
            }
    fun deliverRacun (racunId: UUID) : Racun {
        val racun = repository.getReferenceById(racunId)
        val user = userService.getOrgFromUser()

        racun.items.forEach {
            val inventoryItem = InventoryItem(
                id = UUID.randomUUID(),
                quantity = it.quantity,
                unit = it.unit,
                product = it.product,
                lotNumber = null,
                productionDate = null,
                expirationDate = null,
                owner = user,
                custodian = user,
                status = "available",
                deliveries = listOf()
            )
            inventoryRepository.save(inventoryItem)
        }

        val newRacun = Racun(
            id = racun.id,
            items = racun.items,
            racunDate = racun.racunDate,
            racunee = racun.racunee,
            racuner = racun.racuner,
            status = "delivered"

        )

        return repository.save(newRacun)

    }

}