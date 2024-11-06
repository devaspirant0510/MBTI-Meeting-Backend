package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.Follow
import org.ngod.mbtisns.data.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FollowRepository :JpaRepository<Follow,Long>{
    fun findByFollowerAndFollowing(follower:Member,following:Member):Follow?
}