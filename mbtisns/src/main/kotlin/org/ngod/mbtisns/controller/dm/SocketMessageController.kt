package org.ngod.mbtisns.controller.dm

import org.ngod.mbtisns.controller.dm.dto.DmDto
import org.ngod.mbtisns.controller.dm.dto.SendDmDto
import org.ngod.mbtisns.data.entity.enum.MessageType
import org.ngod.mbtisns.data.projection.AccountProjection
import org.ngod.mbtisns.data.projection.DmProjection
import org.ngod.mbtisns.domain.service.AuthService
import org.ngod.mbtisns.domain.service.DmService
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.time.LocalDateTime

@Controller
class SocketMessageController(
    private val dmService: DmService,
    private val accountService: AuthService

) {
    @MessageMapping("/message/{roomId}")
    @SendTo("/topic/public/{roomId}")
    fun handleMessage(@DestinationVariable roomId: Long, message: DmDto): DmProjection {
        val dmData = SendDmDto(
            dmRoomId = roomId,
            senderId = message.accountId,
            message = message.content,
            messageType = MessageType.TEXT
        )
        val dm = dmService.sendMessage(dmData)
        return object: DmProjection{
            override var id = dm.id!!
            override var sender = accountService.viewFindAuthById(dm.sender.id!!)
            override var message = dm.message
            override var messageType = dm.messageType
            override var createdAt = dm.createdAt
        }
    }
}