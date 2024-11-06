package org.ngod.mbtisns.domain.service

import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.data.entity.Article
import org.ngod.mbtisns.data.entity.ArticleLiked
import org.ngod.mbtisns.data.repository.ArticleLikedRepository
import org.ngod.mbtisns.data.repository.ArticleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val repository: ArticleRepository,
    private val jwtService: JwtService,
    private val authService: AuthService,
    private val articleLikedRepository: ArticleLikedRepository
) {
    fun createArticle(article: Article): Article {
        return repository.save(article)
    }
    fun findAllArticle():List<Article>{
        val list = repository.findAll()
        list.map { item->
            item.likeCount = articleLikedRepository.countByArticle(item)
            item
        }
        return list
    }
    fun findAllArticle(page:Int,size:Int): Page<Article> {
        val pageable = PageRequest.of(page,size)
        return repository.findAll(pageable)
    }
    fun findOneArticleById(articleId: Long):Article{
        val findArticle = repository.findById(articleId)
        if(findArticle.isEmpty){
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(),"존재하지 않는 게시물입니다.")
        }
        return findArticle.get()
    }
    fun findAllLikedAtArticleId(id:Long):List<ArticleLiked>{
        return articleLikedRepository.findAllByArticleId(id)

    }
    fun deleteArticleUnLiked(authorization: String,articleId: Long,accountId: Long):Boolean{
        try{
            val jwtUser = jwtService.verifyToken(authorization)
            val account = authService.readLoginUser(jwtUser?.sub!!)
            if(account.id!=accountId){
                throw ApiException(HttpStatus.UNAUTHORIZED.value(),"인증된 유저가 아닙니다.")
            }
            articleLikedRepository.deleteByAccountIdAndArticleId(accountId,articleId)
            return true
        }catch (e:Exception){
            println(e)
            throw ApiException(HttpStatus.BAD_REQUEST.value(),"실패")
        }

    }

    fun updateArticleLiked(authorization: String, articleId:Long, accountId: Long) :ArticleLiked{
        val jwtUser = jwtService.verifyToken(authorization)
        val account = authService.readLoginUser(jwtUser?.sub!!)
        if(account.id!=accountId){
            throw ApiException(HttpStatus.UNAUTHORIZED.value(),"인증된 유저가 아닙니다.")
        }
        val article = findOneArticleById(articleId)
        val checkValidLiked = articleLikedRepository.findByAccountIdAndArticleId(accountId,articleId)
        if(checkValidLiked.size>1){
            throw ApiException(HttpStatus.BAD_REQUEST.value(),"잘못된 요청입니다 : 이미 좋아요를 눌렀습니다.")
        }
        val likedData = ArticleLiked(account = account, article = article)
        println("like data $likedData")
        val likes = articleLikedRepository.save(likedData)
        return likes //updatedArticle.likes?.toList()?: listOf()
    }
    fun findAllArticleByAccount(accountUid: String):List<Article>{
        val account = authService.readLoginUser(accountUid)
        val list = repository.findAllByAccountId(account.id!!).map { item->
            item.likeCount = articleLikedRepository.countByArticle(item)
            item
        }
        return list
    }
}