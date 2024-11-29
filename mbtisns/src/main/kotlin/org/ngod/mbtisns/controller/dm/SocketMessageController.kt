package org.ngod.mbtisns.controller.dm

import org.ngod.mbtisns.controller.dm.dto.DmDto
import org.ngod.mbtisns.controller.dm.dto.SendDmDto
import org.ngod.mbtisns.core.webrtc.SignalModel
import org.ngod.mbtisns.data.entity.enum.MessageType
import org.ngod.mbtisns.data.projection.DmProjection
import org.ngod.mbtisns.domain.service.AuthService
import org.ngod.mbtisns.domain.service.DmService
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class SocketMessageController(
    private val dmService: DmService,
    private val accountService: AuthService

) {
    private val peers = mutableSetOf<String>()

    //offer 정보를 주고 받기 위한 websocket
    //camKey : 각 요청하는 캠의 key , roomId : 룸 아이디
    @MessageMapping("/peer/offer/{camKey}/{roomId}")
    @SendTo("/topic/peer/offer/{camKey}/{roomId}")
    fun PeerHandleOffer(
        @Payload offer: String, @DestinationVariable(value = "roomId") roomId: String?,
        @DestinationVariable(value = "camKey") camKey: String?
    ): String {
        println("[OFFER] {} : {}, $camKey, $offer")
        return offer
    }

    //iceCandidate 정보를 주고 받기 위한 webSocket
    //camKey : 각 요청하는 캠의 key , roomId : 룸 아이디
    @MessageMapping("/peer/iceCandidate/{camKey}/{roomId}")
    @SendTo("/topic/peer/iceCandidate/{camKey}/{roomId}")
    fun PeerHandleIceCandidate(
        @Payload candidate: String,
        @DestinationVariable(value = "roomId") roomId: String?,
        @DestinationVariable(value = "camKey") camKey: String?
    ): String {
        println("[ICECANDIDATE] {} : {} $camKey, $candidate")
        return candidate
    }


    //
    @MessageMapping("/peer/answer/{camKey}/{roomId}")
    @SendTo("/topic/peer/answer/{camKey}/{roomId}")
    fun PeerHandleAnswer(
        @Payload answer: String, @DestinationVariable(value = "roomId") roomId: String?,
        @DestinationVariable(value = "camKey") camKey: String?
    ): String {
        println("[ANSWER] {} : {}"+camKey+answer)
        return answer
    }

    //camKey 를 받기위해 신호를 보내는 webSocket
    @MessageMapping("/call/key")
    @SendTo("/topic/call/key")
    fun callKey(@Payload message: String): String {
        println("[Key] : {}"+ message)
        return message
    }

    //자신의 camKey 를 모든 연결된 세션에 보내는 webSocket
    @MessageMapping("/send/key")
    @SendTo("/topic/send/key")
    fun sendKey(@Payload message: String): String {
        return message
    }


    @MessageMapping("/register")
    @SendTo("/topic/peers")
    fun registerPeer(peerId: String): List<String> {
        peers.add(peerId)
        return peers.toList()
    }

    @MessageMapping("/signal")
    @SendTo("/topic/signals")
    fun handleSignal(signal: SignalModel) :SignalModel{
        // 피어 간 시그널링 메시지 처리
        return signal
    }
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