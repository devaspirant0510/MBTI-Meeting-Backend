package org.ngod.mbtisns.data.projection

import org.ngod.mbtisns.data.entity.enum.MessageType
import java.time.LocalDateTime

interface DmProjection {
    var id:Long
    var sender:AccountProjection
    var message:String
    var messageType:MessageType
    var createdAt:LocalDateTime
}