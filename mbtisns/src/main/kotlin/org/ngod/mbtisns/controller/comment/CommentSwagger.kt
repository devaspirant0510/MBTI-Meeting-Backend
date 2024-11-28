package org.ngod.mbtisns.controller.comment

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.ngod.mbtisns.controller.comment.dto.RequestCommentDto
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Comment

@Tag(name = "Comment API", description = "댓글 API")
interface CommentSwagger {
    @Operation(summary = "댓글작성", description = "댓글 작성 API")
    fun saveComment(authorization:String,comment: RequestCommentDto):ApiResult<Comment>
    @Operation(summary = "댓글 조회", description = "특정 게시글의 댓글 조회 API")
    fun findAllCommentByArticle(authorization: String,articleId:Long):ApiResult<List<Comment>>
    @Operation(summary = "댓글 삭제", description = "댓글 삭제 API")
    fun deleteComment(authorization: String,commentId:Long):ApiResult<Boolean>
}