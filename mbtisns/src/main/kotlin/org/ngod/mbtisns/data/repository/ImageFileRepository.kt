package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.ImageFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageFileRepository :JpaRepository<ImageFile,Long>{
}