package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.ArticleImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleImageRepository:JpaRepository<ArticleImage,Long> {

}