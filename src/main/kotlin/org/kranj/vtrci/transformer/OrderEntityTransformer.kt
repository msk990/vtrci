package org.kranj.vtrci.transformer

import org.kranj.vtrci.dtos.NewOrderDto
import org.kranj.vtrci.model.Delivery
import org.kranj.vtrci.model.OrderEntity
import org.kranj.vtrci.model.Organization
import org.kranj.vtrci.repository.ItemsRepository
import org.kranj.vtrci.repository.OrganizationRepository
import org.kranj.vtrci.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class OrderEntityTransformer: Transformer<NewOrderDto, OrderEntity> {

    @Autowired
    lateinit var itemsRepository: ItemsRepository

    @Autowired
    lateinit var organizationRepository: OrganizationRepository

    @Autowired
    lateinit var userService: UserService


    override fun transform(source: NewOrderDto): OrderEntity {


        val weAre: Organization? = userService.getOrgFromUser()
        val counterParty = organizationRepository.getReferenceById(UUID.fromString(source.counterParty))

        val buyer = if (source.transaction == "buy")  weAre else counterParty

        val seller = if (buyer == weAre) counterParty else weAre

        val item = itemsRepository.getReferenceById(UUID.fromString(source.itemId))

        val deliveryDates = source.deliveryDates

        var deliveries: MutableList<Delivery> = mutableListOf()

        var orderEntity : OrderEntity = OrderEntity(
            id = UUID.randomUUID(),
            item = item,
            quantity = source.quantity,
            unit = source.unit,
            seller = seller,
            buyer = buyer,
            deliverer = seller,
            proposer = weAre,
            deliveries = null,
            status = "proposed"

        )


        deliveryDates.forEach { dt ->
            var ju = Delivery(
                id=null,
                item = item,
               quantity = source.quantity,
               unit = source.unit,
                seller = seller,
                buyer = buyer,
                deliverer = seller,
               deliveryDate =  dt.plus(8, ChronoUnit.HOURS),
               inventory =  listOf(),
                status = "unpacked"
            )
            ju.order = orderEntity
            deliveries.add(ju)
        }

        orderEntity.deliveries = deliveries
        return orderEntity
    }
}