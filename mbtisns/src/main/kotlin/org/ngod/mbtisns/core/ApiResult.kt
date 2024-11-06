package org.ngod.mbtisns.core

data class ApiResult<T>(
    var success: Boolean,
    var message: String,
    var data: T?=null,
    var error: ApiError? = null
) {
    companion object {
        fun <T> success(data: T, message: String = "success"): ApiResult<T> {
            return ApiResult(success = true, message = message, data = data,error=null)
        }
        fun <T> error(error:ApiError,message:String="An unexpected error occurred."):ApiResult<T>{
            return ApiResult(success = false, message = message, error =error)
        }
    }
}