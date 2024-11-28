package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.DmRoom
import org.ngod.mbtisns.data.projection.DmRoomProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DmRoomRepository:JpaRepository<DmRoom,Long> {
    fun findDmRoomById(id:Long):Optional<DmRoomProjection>
}