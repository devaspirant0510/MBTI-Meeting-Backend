package org.ngod.mbtisns.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.ngod.mbtisns.data.entity.enum.DmRoleType
import java.time.LocalDateTime

@Entity
@Schema
data class AccountDmRoom(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null,

    @CreationTimestamp
    @Column
    var joinedAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    var account: Account,

    @JsonBackReference
    @ManyToOne
    var dmRoom: DmRoom,

    @Enumerated(EnumType.STRING)
    var role: DmRoleType
)