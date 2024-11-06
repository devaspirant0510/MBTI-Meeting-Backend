package org.ngod.mbtisns.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Schema
data class ArticleLiked(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    var account: Account,

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    @JsonIgnoreProperties("likes",)
    var article: Article,

    var likedAt: LocalDateTime = LocalDateTime.now()
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as ArticleLiked

        if (id != null && other.id != null) {
            return id == other.id
        }
        return account == other.account && article == other.article && likedAt == other.likedAt
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: (account.hashCode() + article.hashCode() + likedAt.hashCode())
    }

    override fun toString(): String {
        return "ArticleLiked(id=$id, account=${account.id}, article=${article.id}, likedAt=$likedAt)"
    }
}