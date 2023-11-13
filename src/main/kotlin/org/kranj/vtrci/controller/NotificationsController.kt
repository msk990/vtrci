package org.kranj.vtrci.controller


import org.kranj.vtrci.dtos.NewDeliveryNotificationDto
import org.kranj.vtrci.dtos.NewItemDto
import org.kranj.vtrci.model.Item
import org.kranj.vtrci.model.Notification
import org.kranj.vtrci.service.NotificationService
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("api/v1/notifications")
@RestController
class NotificationsController (val service: NotificationService) {

    @CrossOrigin
    @GetMapping
    fun getNotifications() = service.getMyNotifications()

    @CrossOrigin
    @GetMapping("/count")
    fun getNotificationsCount(pageable: Pageable) = service.getCountMyNotifications()

    @CrossOrigin
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postDeliveryNotification(@RequestBody notification: NewDeliveryNotificationDto): Notification = service.createDeliveryNotification(
     UUID.fromString(notification.orgId), UUID.fromString(notification.deliveryId), notification.msg
    )

    @CrossOrigin
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun updateDeliveryNotification( @PathVariable id: UUID) = service.changeStatus(id)


}