package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.InventoryDto
import org.kranj.vtrci.dtos.NewOrderDto
import org.kranj.vtrci.dtos.OrderDto
import org.kranj.vtrci.dtos.ProductDto
import org.kranj.vtrci.model.OrderEntity
import org.kranj.vtrci.repository.OrderRepository
import org.kranj.vtrci.transformer.OrderEntityTransformer
import org.kranj.vtrci.transformer.OrderPageableTransformer
import org.kranj.vtrci.transformer.toInventoryDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class OrderService (
    val repository: OrderRepository,
    val orderEntityTransformer: OrderEntityTransformer,
    val userService: UserService,
    val orderPageableTransformer: OrderPageableTransformer
        ) {

    fun getMyOrders(name:String,pageable: Pageable) :
        //   Page<OrderEntity>?
    Page<OrderDto>?
    {
        val user = userService.getOrgFromUser()
        val orgId = user?.id
      // return repository.findByItem_ItemNameContainingIgnoreCaseAndSeller_IdEqualsOrBuyer_IdEquals(name, orgId, orgId, pageable)
        return orderPageableTransformer.transform(repository.findByItem_ItemNameContainingIgnoreCaseAndSeller_IdIsOrBuyer_IdIs(name, orgId, orgId, pageable))
    }

    fun getById(id: UUID): OrderEntity = repository.findByIdOrNull(id) ?:
    throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(order: NewOrderDto): OrderEntity = repository.save(orderEntityTransformer.transform(order))
}