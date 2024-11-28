package org.ngod.mbtisns.controller.comment.dto

data class RequestCommentDto(
    var content:String,
    var likeCount:Long,
    var articleId:Long,
    var accountId:Long
)
