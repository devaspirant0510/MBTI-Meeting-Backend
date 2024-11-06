package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Article
import org.ngod.mbtisns.data.entity.ArticleLiked
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Repository
interface ArticleLikedRepository :JpaRepository<ArticleLiked,Long>{
    fun findAllByArticleId(id:Long):List<ArticleLiked>
    fun findAllByAccountId(id:Long):List<ArticleLiked>
    fun findByAccountIdAndArticleId(accountId: Long,articleId: Long):List<ArticleLiked>
    fun countByArticle(article:Article):Int
    @Transactional
    fun deleteByAccountIdAndArticleId(accountId:Long,articleId:Long):Unit
}