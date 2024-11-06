package org.ngod.mbtisns.controller.auth.dto

data class JoinAuthDto(
    var provider:String,
    var uid:String,
    var email:String,
)