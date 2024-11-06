package org.ngod.mbtisns.controller.dm.dto

import org.ngod.mbtisns.data.entity.enum.MessageType

data class SendDmDto(
    var dmRoomId:Long,
    var senderId:Long,
    var messageType:MessageType,
    var message:String
)
