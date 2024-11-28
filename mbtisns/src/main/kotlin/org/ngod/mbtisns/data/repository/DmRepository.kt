package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Dm
import org.ngod.mbtisns.data.projection.DmProjection
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DmRepository:JpaRepository<Dm,Long> {
    fun findDmById(id:Long):Optional<DmProjection>
    fun findAllByDmRoomId(roomId:Long):List<DmProjection>
    fun findAllByDmRoomId(roomId: Long,page:PageRequest):Page<DmProjection>
}