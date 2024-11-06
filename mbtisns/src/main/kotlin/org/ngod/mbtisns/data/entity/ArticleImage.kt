package org.ngod.mbtisns.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Schema
data class ArticleImage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @OneToOne
    @JoinColumn(name = "image_file_id", unique = true)
    var file: ImageFile,
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id") // 외래키 설정 (Article의 id를 참조)
    var article: Article? = null
)
