package org.ngod.mbtisns.domain.model.jwt

data class JwtBody(
    val iss: String,
    val sub: String,
    val aud: String,
    val exp: Long,
    val iat: Long,
    val email: String,
    val phone: String?,
    val app_metadata: AppMetadata,
    val user_metadata: UserMetadata,
    val role: String,
    val aal: String,
    val amr: List<Amr>,
    val session_id: String,
    val is_anonymous: Boolean
)