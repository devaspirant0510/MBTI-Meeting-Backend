package org.ngod.mbtisns.core

class ApiException(
    val code: Int,
    override val message: String
) : RuntimeException(message)
