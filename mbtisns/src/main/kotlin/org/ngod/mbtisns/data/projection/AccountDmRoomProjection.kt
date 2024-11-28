package org.ngod.mbtisns.data.projection

import org.ngod.mbtisns.data.entity.enum.DmRoleType
import java.time.LocalDateTime

interface AccountDmRoomProjection {
    val id:Long
    val joinedAt:LocalDateTime
    val account:AccountProjection
    var role:DmRoleType
}