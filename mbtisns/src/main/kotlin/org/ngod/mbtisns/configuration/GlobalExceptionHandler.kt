package org.ngod.mbtisns.configuration

import org.ngod.mbtisns.core.ApiError
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.core.ApiResult
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ApiException::class)
    fun handleApiException(ex: ApiException): ResponseEntity<ApiResult<Any>> {
        val apiError = ApiError(code = ex.code, status = HttpStatus.valueOf(ex.code))
        val apiResult = ApiResult<Any>(success = false, data = null, message = ex.message, error = apiError)
        return ResponseEntity(apiResult, HttpStatus.valueOf(ex.code))
    }

    // 모든 일반 예외 처리
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ApiResult<Any>> {
        println(ex)
        ex.printStackTrace()
        val apiError = ApiError(code = HttpStatus.INTERNAL_SERVER_ERROR.value(), status = HttpStatus.INTERNAL_SERVER_ERROR)
        val apiResult = ApiResult<Any>(success = false, data = null, message = "An unexpected error occurred.", error = apiError)
        return ResponseEntity(apiResult, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}