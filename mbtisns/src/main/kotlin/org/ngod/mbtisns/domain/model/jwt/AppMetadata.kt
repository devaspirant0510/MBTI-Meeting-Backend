package org.ngod.mbtisns.domain.model.jwt

data class AppMetadata(
    val provider: String,
    val providers: List<String>
)