package org.ngod.mbtisns.data.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import org.hibernate.annotations.CreationTimestamp
import org.ngod.mbtisns.data.entity.enum.MessageType
import java.time.LocalDateTime

@Entity
@Schema
data class Dm(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null,
    @ManyToOne()
    var sender: Account,
    @ManyToOne()
    var dmRoom: DmRoom,
    @Column
    var message:String,
    @Column
    var messageType: MessageType,
    @CreationTimestamp
    var createdAt:LocalDateTime = LocalDateTime.now()
)
