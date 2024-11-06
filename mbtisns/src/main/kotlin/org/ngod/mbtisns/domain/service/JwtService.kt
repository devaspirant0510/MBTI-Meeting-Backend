package org.ngod.mbtisns.domain.service

import com.google.gson.Gson
import com.google.gson.JsonIOException
import io.jsonwebtoken.*
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.domain.model.jwt.JwtBody
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {
    @Value("\${JWT_SECRETKEY}")
    lateinit var secretKey: String

    fun generateMasterToken(): String {
        // 토큰 유효 기간을 10년으로 설정
        val now = Date()
        val expiryDate = Date(now.time + 10 * 365 * 24 * 60 * 60 * 1000L) // 10년

        // JWT 생성
        val jwtValue = Jwts.builder()
            .setSubject("fbc40e18-18a4-4024-8a3d-c24987d92fb5") // 토큰의 주제
            .setIssuedAt(now) // 발급 시간
            .setExpiration(expiryDate) // 만료 시간
            .claim("id", 1) // 추가 데이터
            .claim("provider", "google")
            .claim("sub", "fbc40e18-18a4-4024-8a3d-c24987d92fb5")
            .claim("email", "mgst7411@gmail.com")
            .claim("createdAt", "2024-10-04T10:21:39.243214")
            .claim("member", mapOf(
                "id" to 1,
                "nickName" to "망고소다",
                "mbti" to "INFP",
                "createdAt" to "2024-10-04T10:21:43.649451",
                "profileImage" to mapOf(
                    "id" to 1,
                    "file" to mapOf(
                        "id" to 1,
                        "profileUrl" to "/Users/kotlinandnode/seungho/uploads/스크린샷 2024-10-02 오후 11.15.24.png",
                        "createdAt" to null
                    )
                )
            ))
            .signWith(SignatureAlgorithm.HS256, secretKey.toByteArray()) // 서명 알고리즘
            .compact() // 최종 JWT 문자열 생성
        return jwtValue
    }

    fun verifyToken(token: String): JwtBody? {
        try {
            var accessToken = token
            // Bearer 토큰일 경우 파싱
            if (token.startsWith("Bearer ")) {
                accessToken = token.substring(7)
            }
            val data = Jwts.parser()
                .setSigningKey(secretKey.toByteArray())
                .parseClaimsJws(accessToken).body
            println("data = ${data}")
            val gson = Gson()
            val claimsJson = gson.toJson(data)
            println(claimsJson)
            val jwtBody = gson.fromJson(claimsJson, JwtBody::class.java)
            println(jwtBody)
            return jwtBody
        } catch (e: ExpiredJwtException) {
            println("토큰이 만료되었습니다: ${e.message}")
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "토큰이 만료되었습니다: ${e.message}")
        } catch (e: SignatureException) {
            println("토큰 서명이 유효하지 않습니다: ${e.message}")
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "토큰 서명이 유효하지 않습니다: ${e.message}")
        } catch (e: MalformedJwtException) {
            println("잘못된 형식의 JWT 토큰입니다: ${e.message}")
            throw ApiException(HttpStatus.BAD_REQUEST.value(), "잘못된 형식의 JWT 토큰입니다: ${e.message}")
        } catch (e: JwtException) {
            println("JWT 토큰 검증 중 오류 발생: ${e.message}")
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "JWT 토큰 검증 중 오류 발생: ${e.message}")
        } catch (e: JsonIOException) {
            println("알 수 없는 오류 발생: ${e.message}")
            throw ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "알 수 없는 오류 발생: ${e.message}")
        } catch (e: Exception) {
            println("알 수 없는 오류 발생: ${e.message}")
            throw ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "알 수 없는 오류 발생: ${e.message}")
        }
    }
}
