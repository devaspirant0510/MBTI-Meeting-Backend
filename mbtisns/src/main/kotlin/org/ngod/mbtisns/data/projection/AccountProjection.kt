package org.ngod.mbtisns.data.projection

interface  AccountProjection {
    val id:Long?
    var uid:String
    val member:MemberProjection
}