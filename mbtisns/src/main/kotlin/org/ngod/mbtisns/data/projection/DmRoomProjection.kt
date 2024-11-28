package org.ngod.mbtisns.data.projection

import java.time.LocalDateTime

interface DmRoomProjection {
    val id:Long
    val roomName:String
    val createdAt:LocalDateTime
    val accountDmRooms:List<AccountDmRoomProjection>

}