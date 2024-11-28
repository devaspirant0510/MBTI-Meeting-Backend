package org.ngod.mbtisns.data.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null,
    @Column
    var content: String,
    @CreationTimestamp
    @Column()
    var createdAt: LocalDateTime = LocalDateTime.now(),
    @Column()
    var likeCount: Long=0,
    @ManyToOne
    var article: Article,
    @ManyToOne
    var account: Account,

)