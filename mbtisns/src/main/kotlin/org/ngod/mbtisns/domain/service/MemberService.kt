package org.ngod.mbtisns.domain.service

import io.jsonwebtoken.Jwts
import lombok.extern.slf4j.Slf4j
import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.data.entity.ArticleLiked
import org.ngod.mbtisns.data.entity.Follow
import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.data.entity.ProfileImage
import org.ngod.mbtisns.data.repository.ArticleLikedRepository
import org.ngod.mbtisns.data.repository.AuthRepository
import org.ngod.mbtisns.data.repository.FollowRepository
import org.ngod.mbtisns.data.repository.MemberRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import kotlin.IllegalStateException

@Slf4j
@Service
class MemberService(
    private val repository: MemberRepository,
    private val authRepository: AuthRepository,
    private val followRepository: FollowRepository,
    private val likedRepository: ArticleLikedRepository,
    private final val authService: AuthService
) {
    fun getLikedArticlesForMember(accountId:Long):List<ArticleLiked>{
        return likedRepository.findAllByAccountId(accountId)
    }

    fun checkMemberAuthorization(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey("wlswkflarhkwmqtpdusdhzld".toByteArray()).parseClaimsJws(token)
            print(claims)
            print(claims.body)
            print(claims.header)
            print(claims.signature)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun memberSave(memberDTO: JoinMemberDTO): Member {
        val convertMember = memberDTO.member
        val member = Member(
            id = memberDTO.accountId,
            mbti = convertMember.mbti,
            nickName = convertMember.nickName,
            gender = convertMember.gender
        )
        val account = authRepository.findById(memberDTO.accountId).orElseThrow {
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(), "계정이 존재하지 않습니다.")
        }
        val savedMember = repository.save(member)
        account.member = savedMember
        authRepository.save(account)
        return savedMember
    }

    fun getMemberById(id: Long): Member {
        val member = repository.findById(id)
        if (member.isEmpty) {
            throw ApiException(HttpStatus.UNPROCESSABLE_ENTITY.value(), "유저를 찾을수 없습니다.")
        }
        return member.get()
    }

    fun updateMemberProfileImage(memberId: Long, profileImage: ProfileImage): Member {
        getMemberById(memberId).profileImage = profileImage
        return getMemberById(memberId)
    }

    fun followUser(myId: Long, followId: Long): Follow {
        val my = getMemberById(myId)
        val following = getMemberById(followId)
        val follow = Follow(follower = following, following = my)
        val savedFollow = followRepository.save(follow)
        my.followers?.add(savedFollow) // 내 followings에 추가
        following.followings?.add(savedFollow) // 팔로우한 사람의 followers에 추가
        return savedFollow
    }

    fun unFollowUser(myId: Long, followId: Long): Follow {

        val my = getMemberById(myId)
        val follower = getMemberById(followId)
        val findFollow = followRepository.findByFollowerAndFollowing(my, follower)
        if (findFollow == null) {
            throw ApiException(HttpStatus.BAD_REQUEST.value(), "팔로우 취소 실패 : 잘몬된 연결입니다.")
        }
        my.followers?.remove(findFollow)
        follower.followers?.remove(findFollow)
        followRepository.delete(findFollow)
        return findFollow
    }

    fun getRecommendUser(uid: String): List<Member> {
        val account = authService.readLoginUser(uid)
        return repository.findRandomAccount(account.id!!)
    }
}