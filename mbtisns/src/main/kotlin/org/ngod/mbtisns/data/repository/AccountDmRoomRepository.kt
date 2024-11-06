package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.AccountDmRoom
import org.springframework.data.jpa.repository.JpaRepository

interface AccountDmRoomRepository :JpaRepository<AccountDmRoom,Long>{
    fun findAllByAccountId(accountId:Long):List<AccountDmRoom>
}