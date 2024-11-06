package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.ProfileImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProfileImageRepository:JpaRepository<ProfileImage,Long> {
    fun findByMemberId(memberId:Long): Optional<ProfileImage>
}