package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.projection.AccountProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AuthRepository :JpaRepository<Account,Long>{
    fun findByUid(uid:String):Optional<Account>
    fun findAccountById(id:Long):Optional<AccountProjection>
}