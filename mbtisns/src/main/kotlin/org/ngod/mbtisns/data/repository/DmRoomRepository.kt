package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.DmRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DmRoomRepository:JpaRepository<DmRoom,Long> {
}