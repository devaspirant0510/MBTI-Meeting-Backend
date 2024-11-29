package org.ngod.mbtisns.core.webrtc

data class SignalModel(
    val from:String?=null,
    val to:String?=null,
    val type:String?=null,
    val sdp:String?=null,
    val candidate:String?=null
)