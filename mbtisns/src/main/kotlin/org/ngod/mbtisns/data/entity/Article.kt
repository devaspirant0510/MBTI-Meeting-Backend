package org.ngod.mbtisns.data.entity

import com.fasterxml.jackson.annotation.*
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import kotlin.jvm.Transient

@Schema
@Entity
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnoreProperties("followings","followers","follower","following")
    var account: Account,

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    @JsonManagedReference // 순환 참조 방지를 위한 설정
    var images: MutableSet<ArticleImage>? = HashSet(),

    @CreationTimestamp
    @Column()
    var createdAt:LocalDateTime = LocalDateTime.now(),

    @Transient
    var likeCount:Int=0



//    @JsonBackReference
//    @OneToMany(mappedBy="article" ,fetch = FetchType.LAZY)
//    var likes: MutableSet<ArticleLiked>? = HashSet()
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Article

        // ID가 있으면 ID로 비교, 없으면 필드로 비교
        if (id != null && other.id != null) {
            return id == other.id
        }

        return content == other.content && account == other.account
    }

    override fun hashCode(): Int {
        // ID가 있으면 ID로 해시 계산, 없으면 기본 필드로 해시 계산
        return id?.hashCode() ?: (content.hashCode() + (account.hashCode()))
    }
}
