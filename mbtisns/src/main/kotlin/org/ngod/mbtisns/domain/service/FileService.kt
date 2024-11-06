package org.ngod.mbtisns.domain.service

import org.ngod.mbtisns.data.entity.*
import org.ngod.mbtisns.data.entity.enum.FileType
import org.ngod.mbtisns.data.repository.ArticleImageRepository
import org.ngod.mbtisns.data.repository.ImageFileRepository
import org.ngod.mbtisns.data.repository.ProfileImageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileService(
    private val repository: ImageFileRepository,
    private val profileImageRepository: ProfileImageRepository,
    private val articleImageRepository: ArticleImageRepository,
    private val supabaseService:SupabaseService
) {
    companion object {
        const val UPLOAD_PATH = "/Users/kotlinandnode/seungho/uploads"
    }
    fun checkedUserHasProfileImage(memberId:Long):Boolean{
        val findMember = profileImageRepository.findByMemberId(memberId)
        if(findMember.isEmpty){
            return false
        }
        return true

    }
    fun saveFileProfileImage(url:String,member: Member):ProfileImage{
        val imageFile = ImageFile(profileUrl = url, imageType = FileType.PROFILE)
        repository.save(imageFile)
        val profileImage = ProfileImage(file = imageFile, member = member)
        return profileImageRepository.save(profileImage)
    }
    fun saveFileArticle(file:MultipartFile,article:Article):ArticleImage{
        val uploadDir = UPLOAD_PATH
        val path = Paths.get(uploadDir + File.separator + file.originalFilename)
        println("$uploadDir $path")
        Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
        val imageFile = ImageFile(profileUrl = path.toString())
        val savedImage = repository.save(imageFile)
        val articleImage = ArticleImage(file = savedImage, article = article)
        return articleImageRepository.save(articleImage)
    }

}