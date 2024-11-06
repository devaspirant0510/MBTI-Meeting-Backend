package org.ngod.mbtisns.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
data class DmRoom(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,
    @Column
    var roomName:String="",
    @CreationTimestamp
    var createdAt:LocalDateTime?=null,
    @OneToMany(mappedBy = "dmRoom", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var accountDmRooms: List<AccountDmRoom> = mutableListOf()
)
