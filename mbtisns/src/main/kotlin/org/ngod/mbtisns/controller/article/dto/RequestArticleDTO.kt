package org.ngod.mbtisns.controller.article.dto

import org.springframework.web.multipart.MultipartFile

data class RequestArticleDTO(
    var content:String,
    var images:List<MultipartFile>?
)
