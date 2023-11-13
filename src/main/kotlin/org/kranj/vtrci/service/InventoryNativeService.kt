package org.kranj.vtrci.service

import org.kranj.vtrci.dtos.InventoryNativeDto
import org.kranj.vtrci.repository.InventoryNativeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InventoryNativeService (
    val repository: InventoryNativeRepository,
    val userService: UserService
        ) {

    fun getByProductAndOwner(productId: UUID) : List<InventoryNativeDto?>?{
        val owner = userService.getOrgFromUser()
        val orgId = owner?.id
        return orgId?.let { repository.findByProductAndOwner(productId, it) }
    }

    fun getByItemAndOwner(itemId: UUID) : List<InventoryNativeDto?>?{
        val owner = userService.getOrgFromUser()
        val orgId = owner?.id
        return orgId?.let { repository.findByItemAndOwner(itemId, it) }
    }

    fun getUnpackedByItemAndOwner(itemId: UUID) : List<InventoryNativeDto?>?{
        val owner = userService.getOrgFromUser()
        val orgId = owner?.id
        return orgId?.let { repository.findUnpackedByItemAndOwner(itemId, it) }
    }
}