package org.ngod.mbtisns.domain.model.jwt

data class UserMetadata(
    val avatar_url: String,
    val email: String,
    val email_verified: Boolean,
    val full_name: String,
    val iss: String,
    val name: String,
    val phone_verified: Boolean,
    val picture: String,
    val provider_id: String,
    val sub: String
)