package org.ngod.mbtisns.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Schema(name = "Follow", description = "팔로우 관계")
@Entity
data class Follow(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    @JsonIgnoreProperties("followings","followers")
    var follower: Member, // 팔로워

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", nullable = false)
    @JsonIgnoreProperties("followings","followers")
    var following: Member // 팔로잉
){
    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Follow) return false
        return id == other.id
    }
}
