package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Dm
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DmRepository:JpaRepository<Dm,Long> {
    fun findAllByDmRoomId(roomId:Long):List<Dm>
    fun findAllByDmRoomId(roomId: Long,page:PageRequest):Page<Dm>
}