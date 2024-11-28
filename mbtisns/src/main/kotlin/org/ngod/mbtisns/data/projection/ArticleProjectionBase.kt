package org.ngod.mbtisns.data.projection

interface ArticleProjectionBase :ArticleProjection{
    val likeCount:Long
    val commentCount:Long
}