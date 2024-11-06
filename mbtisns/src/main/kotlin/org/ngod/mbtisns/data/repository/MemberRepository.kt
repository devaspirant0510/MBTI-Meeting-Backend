package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.data.entity.ProfileImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface MemberRepository :JpaRepository<Member,Long>{
    @Query("SELECT * FROM member WHERE id!=:userId ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
    fun findRandomAccount(@Param("userId")userId:Long): List<Member>
}