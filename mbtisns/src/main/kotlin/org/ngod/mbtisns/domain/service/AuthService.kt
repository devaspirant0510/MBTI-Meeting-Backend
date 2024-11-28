package org.ngod.mbtisns.domain.service

import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.projection.AccountProjection
import org.ngod.mbtisns.data.repository.AuthRepository
import org.ngod.mbtisns.domain.model.jwt.JwtBody
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(val repository: AuthRepository) {
    fun userLogin(auth:JwtBody):Account{
        val account = readLoginUser(auth.sub)
        if(account.member==null){
            throw ApiException(HttpStatus.BAD_REQUEST.value(),"sns 계정은 등록됬지만 서비스 가입이 안됬습니다. 회원가입 해주세요")
        }
        return account

    }
    fun findIsJoinedMember(uid:String):Boolean{
        val user = repository.findByUid(uid).orElseThrow{
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(),"계정을 찾을수 없습니다.")
        }
        if(user.member==null){
            return false
        }
        return true
    }
    fun viewFindAuthById(accountId:Long):AccountProjection{
        val findAuth = repository.findAccountById(accountId).orElseThrow {
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(),"계정을 찾을수 없습니다.")
        }
        return findAuth
    }
    fun findAuthById(accountId:Long):Account{
        val findAuth = repository.findById(accountId).orElseThrow {
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(),"계정을 찾을수 없습니다.")
        }
        return findAuth

    }
    fun accountSave(account: Account): Account {
        return repository.save(account);
    }

    fun readLoginUser(uid: String): Account {
        val findUser = repository.findByUid(uid)
        if(findUser.isEmpty){
            throw IllegalStateException("유저를 찾는데 실패했습니다.")
        }
        return findUser.get()

    }
}