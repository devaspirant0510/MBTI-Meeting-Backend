package org.ngod.mbtisns.controller.member

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.ArticleLiked
import org.ngod.mbtisns.data.entity.Member
import org.springframework.web.multipart.MultipartFile

@Tag(name = "Member Api", description = "멤버 관련 API")
interface MemberSwagger {
    @Operation(summary = "유저의 좋아요 목록", description = "특정 id 유저의 좋아요를 누를 게시글 리스트")
    fun getLikedForMemberId(authorization: String,accountId:Long):ApiResult<List<ArticleLiked>>

    @Operation(summary = "팔로우", description = "팔로우 취소")
    fun followUser(authorization: String, myId: Long, followId: Long): ApiResult<Account>;

    @Operation(summary = "팔로우 취소", description = "팔로우 취소")
    fun unFollowUser(authorization: String, myId: Long, followId: Long): ApiResult<Account>

    @Operation(summary = "유저 조회", description = "id 를 기반으로 유저 조회")
    fun getMember(authorization: String, memberId: Long): ApiResult<Member>

    @Operation(summary = "서비스 회원가입", description = "sns 인증후 서비스 회원가입 ")
    fun joinMember(authorization: String, member: JoinMemberDTO): Member

    @Operation(summary = "프로필 이미지 업로드 & 변경", description = "프로필 이미지를 업로드 또는 수정하는 api")
    @ApiResponses(
        value = [
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "이미지 업로드 성공"),
            io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "권한 문제"),
        ]
    )
    suspend fun uploadImage(
        @Parameter(
            description = "액세스 토큰 (Bearer jwt token)",
            required = true,
            name = "Access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw",
        )
        authorization: String,
        @Parameter(
            description = "멀티파트 폼 이미지 데이터",
            required = true,
            name = "multipart",
            example = "멀티파트 이미지 데이터",
        )
        file: MultipartFile
    ): ApiResult<String>

    fun getRecommendUser(authorization: String): ApiResult<List<Member>>
}