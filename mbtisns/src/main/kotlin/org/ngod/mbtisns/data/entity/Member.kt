package org.ngod.mbtisns.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.ngod.mbtisns.data.entity.enum.Mbti
import java.time.LocalDateTime

@Schema(name = "Account", description = "사용자")
@Entity
data class Member(
    @Id
    var id: Long? = null,

    @Schema(name = "nickName", description = "유저 닉네임")
    @Column(name = "nick_name", nullable = true, length = 20, unique = true)
    var nickName: String? = null,

    @Column(name = "gender")
    var gender: Boolean? = null,

    @Enumerated(EnumType.STRING)
    var mbti: Mbti? = null, // Enum type for MBTI

    @CreationTimestamp
    @Schema(name = "createdAt", description = "SNS 계정 연동일")
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

    @OneToOne(mappedBy = "member")
    @JoinColumn(name = "profile_image_id", nullable = true)
    var profileImage: ProfileImage? = null,


    @OneToMany(mappedBy = "follower", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var followings: MutableSet<Follow>? = HashSet(), // 팔로우한 사람들

    @OneToMany(mappedBy = "following", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var followers: MutableSet<Follow>? = HashSet() // 나를 팔로우하는 사람들

)
