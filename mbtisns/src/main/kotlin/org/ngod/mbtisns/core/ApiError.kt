package org.ngod.mbtisns.core

import org.springframework.http.HttpStatus


data class ApiError(
    var code:Int,
    var status: HttpStatus
)
