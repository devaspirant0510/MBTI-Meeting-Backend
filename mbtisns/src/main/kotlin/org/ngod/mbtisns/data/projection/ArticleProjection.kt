package org.ngod.mbtisns.data.projection

import java.time.LocalDateTime

interface ArticleProjection {
    val id:Long
    val content:String
    val account:AccountProjection
    val createdAt:LocalDateTime
}