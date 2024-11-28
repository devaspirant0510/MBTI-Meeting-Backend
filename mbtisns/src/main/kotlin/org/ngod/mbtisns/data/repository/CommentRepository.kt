package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository:JpaRepository<Comment,Long> {
    fun findAllByArticleIdOrderByCreatedAtDesc(articleId: Long):List<Comment>
    fun countByArticleId(articleId: Long):Long
}