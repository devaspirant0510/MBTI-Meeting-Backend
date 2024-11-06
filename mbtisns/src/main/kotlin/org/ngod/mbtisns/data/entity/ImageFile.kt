package org.ngod.mbtisns.data.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.ngod.mbtisns.data.entity.enum.FileType
import java.time.LocalDateTime
import java.util.*

@Schema( description = "유저 프로필 이미지")
@Entity
data class ImageFile(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null,

    @Schema(name = "profileURL", description = "프로필 사진 URL")
    @Column(name = "profile_url", nullable = true, length = 255)
    var profileUrl: String?,

    @Column
    var imageType:FileType=FileType.ARTICLE,

    @CreationTimestamp
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

)