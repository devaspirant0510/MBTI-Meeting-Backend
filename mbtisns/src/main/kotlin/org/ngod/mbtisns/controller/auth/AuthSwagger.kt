package org.ngod.mbtisns.controller.auth

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.ngod.mbtisns.controller.auth.dto.JoinAuthDto
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.projection.AccountProjection
import org.ngod.mbtisns.domain.model.jwt.JwtBody
import org.springframework.http.ResponseEntity

@Tag(name = "Account Api", description = "OAuth 계정 관련 API")
interface AuthSwagger {
    @Operation(summary = "로그인")
    fun login(authorization: String): ApiResult<Account>

    @Operation(summary = "가입된 유저인지 확인", description = "SNS 연동후 uid 로 서비스에 가입했는지 확인", method = "get")
    fun isJoinUser(uid:String):ApiResult<Boolean>

    @Operation(summary = "유저 조회", description = "user Id 로 유저 조회", method = "get")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "마스터 jwt 토큰 발급")])
    fun getAuthById(accountId: Long): ApiResult<AccountProjection>

    @Operation(summary = "마스터 토큰 발급", description = "테스트용 유효기간 없는 토큰", method = "get")
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "마스터 jwt 토큰 발급")])
    fun getMasterToken(): ResponseEntity<ApiResult<String>>

    @Operation(summary = "OAuth 계정 회원가입", description = "sns 연동한 계정 정보 등록")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "계정 등록 성공"),
            ApiResponse(responseCode = "401", description = "유효하지 않는 값입니다."),
        ]
    )
    fun joinAccount(account: JoinAuthDto): Account

    @Operation(summary = "유저 정보 조회", description = "JWT 토큰을 이용해 유저 정보를 조회")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "유저 정보 조회 성공"),
            ApiResponse(responseCode = "400", description = "잘못된 요청")
        ]
    )
    fun getUser(
        @Parameter(
            description = "액세스 토큰 (Bearer jwt token)",
            required = true,
            name = "Access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw",
        )
        token: String
    ): org.ngod.mbtisns.core.ApiResult<Account>

    @Operation(summary = "JWT 인증 확인", description = "JWT 토큰의 유효성을 확인하여 인증 정보를 반환")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "토큰 인증 성공"),
            ApiResponse(responseCode = "401", description = "인증 실패"),
            ApiResponse(responseCode = "400", description = "잘못된 요청")
        ]
    )
    fun getAuth(
        @Parameter(
            description = "액세스 토큰 (Bearer jwt token)",
            required = true,
            name = "Access token",
            example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0NWEyNTcwOC1lNDBiLTRjOGUtOTgzYy0wZTI2NWJmNjY1MzMiLCJpYXQiOjE3MjgxMTQwNDYsImV4cCI6MjA0MzQ3NDA0NiwiaWQiOjEsInByb3ZpZGVyIjoia2FrYW8iLCJlbWFpbCI6Im5vdmEwMjA1MTBAbmF2ZXIuY29tIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTozOS4yNDMyMTQiLCJtZW1iZXIiOnsiaWQiOjEsIm5pY2tOYW1lIjoi7J247YWU65ah7IOB6riw7JuQM-uFhOywqCIsIm1idGkiOiJJTlRQIiwiY3JlYXRlZEF0IjoiMjAyNC0xMC0wNFQxMDoyMTo0My42NDk0NTEiLCJwcm9maWxlSW1hZ2UiOnsiaWQiOjEsImZpbGUiOnsiaWQiOjEsInByb2ZpbGVVcmwiOiIvVXNlcnMva290bGluYW5kbm9kZS9zZXVuZ2hvL3VwbG9hZHMv4YSJ4YWz4YSP4YWz4YSF4YW14Yar4YSJ4YWj4Ya6IDIwMjQtMTAtMDIg4YSL4YWp4YSS4YWuIDExLjE1LjI0LnBuZyIsImNyZWF0ZWRBdCI6bnVsbH19fX0.-qStonxjOkLl3sG6GQ1AUT-o4y_id8zCWUA0gu_YoXw",
        ) token: String
    ): org.ngod.mbtisns.core.ApiResult<JwtBody>

}