package org.kranj.vtrci.service

import org.kranj.vtrci.model.Notification
import org.kranj.vtrci.repository.DeliveryRepository
import org.kranj.vtrci.repository.InventoryItemRepository
import org.kranj.vtrci.repository.NotificationsRepository
import org.kranj.vtrci.repository.OrganizationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*

@Service
class NotificationService(
    val repository: NotificationsRepository,
    val userService: UserService,
    val organizationRepository: OrganizationRepository,
    val deliveryRepository: DeliveryRepository,
    val inventoryItemRepository: InventoryItemRepository
) {

    fun getMyNotifications() : List<Notification> {
        val producer = userService.getOrgFromUser()
        val orgId = producer?.id
        if (orgId != null) {
           return repository.findByOrganizationIdEqualsOrderByIssuedAsc(orgId)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun getCountMyNotifications(): Long {

        val producer = userService.getOrgFromUser()
        val orgId = producer?.id
        if (orgId != null) {
            return repository.countByOrganizationIdEqualsAndStatusIs(orgId, "unread")
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    }

    fun createDeliveryNotification(orgId: UUID?, deliveryId:UUID?, msg:String): Notification {
        val organization = orgId?.let { organizationRepository.getReferenceById(it) }
        val delivery = deliveryId?.let { deliveryRepository.getReferenceById(it) }

        if (organization !=null && delivery !=null){
            val newNotification = Notification(
                id = UUID.randomUUID(),
                delivery = delivery,
                inventory = null,
                message = msg,
                status = "unread",
                organization = organization,
                issued = Instant.now()
            )
            return repository.save(newNotification)
        }
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun changeStatus(id:UUID) :Notification {
        val oldNotification = repository.getReferenceById(id)

        val newNotification = Notification(
            id = oldNotification.id,
            delivery = oldNotification.delivery,
            inventory = oldNotification.inventory,
            message = oldNotification.message,
            status = "read",
            organization = oldNotification.organization,
            issued = Instant.now()
        )
        return repository.save(newNotification)
    }
}