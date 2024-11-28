package org.ngod.mbtisns.controller.article

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.ngod.mbtisns.controller.article.dto.RequestArticleDTO
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Article
import org.ngod.mbtisns.data.entity.ArticleLiked
import org.ngod.mbtisns.data.projection.ArticleProjection

@Tag(name = "Article Api", description = "게시글 관련 API")
interface ArticleSwagger {

    @Operation(summary = "글 작성", description = "x-www-form-urlencoded 방식으로 게시글 작성")
    fun postOneArticle(
        @Parameter(
            description = "액세스 토큰 (Bearer jwt token)",
            required = true,
            name = "Access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw",
        )
        authorization: String,
        requestArticleDTO: RequestArticleDTO
    ): ApiResult<Article>

    @Operation(summary = "한개의 글 조회", description = "url 파라미터의 id를 기준으로 게시글 조회")
    fun findOneArticle(
        @Parameter(
            description = "액세스 토큰 (Bearer jwt token)",
            required = true,
            name = "Access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw",
        )
        authorization: String,
        @Parameter(
            description = "게시글의 id",
            required = true,
            name = "Article Id",
            example = "1"
        )
        articleId: Long
    ): ApiResult<ArticleProjection>

    @Operation(summary = "게시글 좋아요", description = "특정 id 의 게시글에 좋아요")
    fun voteForArticle(
        @Parameter(
            description = "게시글의 id",
            required = true,
            name = "Article Id",
            example = "1"
        )
        articleId: Long,
        @Parameter(
            description = "유저의 id",
            required = true,
            name = "Account Id",
            example = "1"
        )
        accountId: Long,
        @Parameter(
            description = "액세스 토큰 (Bearer jwt token)",
            required = true,
            name = "Access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw",
        )
        authorization: String,
    ): ApiResult<ArticleLiked>
    @Operation(summary = "좋아요 취소", description = "특정 id의 게시글에 좋아요 취소")
    fun unVoteForArticle(
        @Parameter(
            description = "게시글의 id",
            required = true,
            name = "Article Id",
            example = "1"
        )
        articleId: Long,
        @Parameter(
            description = "유저의 id",
            required = true,
            name = "Account Id",
            example = "1"
        )
        accountId: Long,
        @Parameter(
            description = "액세스 토큰 (Bearer jwt token)",
            required = true,
            name = "Access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw",
        )
        authorization: String,
    ): ApiResult<Boolean>



    @Operation(summary = "좋아요 조회", description = "특정 게시글에 누른 좋아요를 조회하는 Api ")
    fun findAllLikedAtArticle(
        authorization: String,
        articleId: Long
    ): ApiResult<List<ArticleLiked>>



    @Operation(summary = "모든 글 조회", description = "전체 글을 조회하는 Api ")
    fun findAllArticle(
        authorization: String
    ): ApiResult<List<ArticleProjection>>

    @Operation(summary = "모든 글 조회(페이징)", description = "페이징을 이용해 전체 글을 조회하는 Api(Recommend)")
    fun findAllArticle(
        authorization: String,
        page:Int,
        size:Int
    ): ApiResult<List<Article>>

    @Operation(summary = "유저의 글 모두 조회", description = "특정 유저가 작성한 글을 모두 조회하는 Api(Recommend)")
    fun findAllArticleByAccount(
        authorization: String,
    ): ApiResult<List<ArticleProjection>>

    @Operation(summary = "특정 유저의 작성 글 모두 조회", description = "특정 유저의 아이디 기준 작성글 모두 조회하는 API")
    fun findAllArticleByAccountId(
        authorization: String,
        accountId: Long
    ): ApiResult<List<ArticleProjection>>
}