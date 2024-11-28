package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.Article
import org.ngod.mbtisns.data.projection.ArticleProjection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ArticleRepository :JpaRepository<Article,Long>{
    fun findArticleById(id:Long): Optional<ArticleProjection>
    fun findAllByAccountId(accountId: Long):List<ArticleProjection>
    fun findAllByAccountIdOrderByCreatedAtDesc(accountId: Long):List<ArticleProjection>
    fun findAllByOrderByCreatedAtDesc():List<ArticleProjection>
}