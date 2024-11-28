package org.ngod.mbtisns.controller.auth

import lombok.extern.slf4j.Slf4j
import org.ngod.mbtisns.controller.auth.dto.JoinAuthDto
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.projection.AccountProjection
import org.ngod.mbtisns.domain.model.jwt.JwtBody
import org.ngod.mbtisns.domain.service.AuthService
import org.ngod.mbtisns.domain.service.JwtService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
class AuthController(val service: AuthService, val jwtService: JwtService) : AuthSwagger {
    @PostMapping("/login")
    override fun login(authorization: String): ApiResult<Account> {
        val jwtUser =
            jwtService.verifyToken(authorization) ?: throw ApiException(HttpStatus.UNAUTHORIZED.value(), "인증 실패")
        return ApiResult.success(service.userLogin(jwtUser))
    }

    @GetMapping("/isJoin")
    override fun isJoinUser(
        @RequestParam(name = "uid")
        uid: String
    ): ApiResult<Boolean> {
        return ApiResult.success(service.findIsJoinedMember(uid))
    }

    @GetMapping("/{accountId}")
    override fun getAuthById(
        @PathVariable(name = "accountId")
        accountId: Long
    ): ApiResult<AccountProjection> {
        return ApiResult.success(service.viewFindAuthById(accountId))
    }

    @GetMapping("/masterToken")
    override fun getMasterToken(): ResponseEntity<ApiResult<String>> {
        return ResponseEntity.ok(ApiResult.success(jwtService.generateMasterToken()))
    }


    @PostMapping()
    override fun joinAccount(@RequestBody account: JoinAuthDto): Account {
        try {
            println(account)
            val convertAccount = Account(uid = account.uid, email = account.email, provider = account.provider)
            return service.accountSave(convertAccount)
        } catch (e: Exception) {
            println(e)
            e.printStackTrace()
            throw Error(e.toString())
        }
    }

    @GetMapping("/getUser")
    override fun getUser(@RequestHeader("Authorization") token: String): ApiResult<Account> {
        try {
            val jwtBody =
                jwtService.verifyToken(token) ?: throw ApiException(HttpStatus.BAD_REQUEST.value(), "jwtToken 인증실패")
            val userUid = jwtBody.sub
            val user = service.readLoginUser(userUid)
            return ApiResult.success(user)
        } catch (e: Error) {
            throw ApiException(HttpStatus.BAD_REQUEST.value(), "bad request")
        }
    }

    @GetMapping("/getAuth")
    override fun getAuth(@RequestHeader("Authorization") token: String): ApiResult<JwtBody> {
        val body = jwtService.verifyToken(token)
        if (body != null) {
            return ApiResult.success(body)
        } else {
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "인증되지 않은 유저")
        }
    }

}