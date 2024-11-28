package org.ngod.mbtisns.controller.comment

import io.ktor.http.*
import org.ngod.mbtisns.controller.comment.dto.RequestCommentDto
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Comment
import org.ngod.mbtisns.data.repository.ArticleRepository
import org.ngod.mbtisns.data.repository.AuthRepository
import org.ngod.mbtisns.data.repository.CommentRepository
import org.ngod.mbtisns.domain.service.JwtService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/comment")
@RestController
class CommentController(
    private val commentRepository: CommentRepository,
    private val articleRepository: ArticleRepository,
    private val authRepository: AuthRepository,
    private val jwtService: JwtService
) : CommentSwagger {
    @PostMapping
    override fun saveComment(
        @RequestHeader("Authorization")
        authorization: String,
        @RequestBody
        comment: RequestCommentDto
    ): ApiResult<Comment> {
        println(comment)
        jwtService.verifyToken(authorization)
        val writeUser = authRepository.findById(comment.accountId)
        if (writeUser.isEmpty) {
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "계정을 찾을수 없습니다.")
        }
        val writeArticle = articleRepository.findById(comment.articleId)
        if (writeArticle.isEmpty) {
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "게시글을 찾을수 없습니다.")
        }
        val mapperComment = Comment(
            content = comment.content,
            account = writeUser.get(),
            article = writeArticle.get(),
        )
        val savedComment = commentRepository.save(mapperComment)
        return ApiResult.success(savedComment)
    }

    @GetMapping("article/{articleId}")
    override fun findAllCommentByArticle(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "articleId")
        articleId: Long
    ): ApiResult<List<Comment>> {
        return ApiResult.success(commentRepository.findAllByArticleIdOrderByCreatedAtDesc(articleId))
    }

    @DeleteMapping("{commentId}")
    override fun deleteComment(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "commentId")
        commentId: Long
    ): ApiResult<Boolean> {
        TODO("Not yet implemented")
    }
}