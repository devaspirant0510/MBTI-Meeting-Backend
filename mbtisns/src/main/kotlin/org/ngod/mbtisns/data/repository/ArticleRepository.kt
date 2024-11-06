package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository :JpaRepository<Article,Long>{
    fun findAllByAccountId(accountId: Long):List<Article>
}