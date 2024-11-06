package org.ngod.mbtisns.controller.member

import lombok.extern.slf4j.Slf4j
import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.ArticleLiked
import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.data.entity.ProfileImage
import org.ngod.mbtisns.data.entity.enum.FileType
import org.ngod.mbtisns.domain.service.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Slf4j
@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    val service: MemberService,
    val authService: AuthService,
    val fileService: FileService,
    val jwtService: JwtService,
    private val supabaseService: SupabaseService
) : MemberSwagger {
    @GetMapping("/like/{accountId}")
    override fun getLikedForMemberId(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "accountId")
        accountId: Long
    ): ApiResult<List<ArticleLiked>> {
        jwtService.verifyToken(authorization)
        return ApiResult.success(service.getLikedArticlesForMember(accountId))
    }

    @PatchMapping("/follow/{myId}/{followId}")
    override fun followUser(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "myId")
        myId: Long,
        @PathVariable(name = "followId")
        followId: Long
    ): ApiResult<Account> {
        println("patch follow $myId $followId")

        val jwt = jwtService.verifyToken(authorization)
        service.followUser(myId, followId)
        return ApiResult.success(authService.readLoginUser(jwt?.sub!!))


    }

    @DeleteMapping("/unfollow/{myId}/{followId}")
    override fun unFollowUser(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "myId")
        myId: Long,
        @PathVariable(name = "followId")
        followId: Long
    ): ApiResult<Account> {
        val jwt = jwtService.verifyToken(authorization)
        service.unFollowUser(myId, followId)
        return ApiResult.success(authService.readLoginUser(jwt?.sub!!))
    }

    @GetMapping("/{memberId}")
    override fun getMember(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable(name = "memberId") memberId: Long
    ): ApiResult<Member> {
        jwtService.verifyToken(authorization)
        return ApiResult.success(service.getMemberById(memberId))

    }


    @PostMapping
    override fun joinMember(
        @RequestHeader("Authorization") token: String,
        @RequestBody member: JoinMemberDTO
    ): Member {
        service.checkMemberAuthorization(token)
        return service.memberSave(member)
    }

    @PostMapping("/upload")
    override suspend fun uploadImage(
        @RequestHeader("Authorization")
        authorization: String,
        @RequestParam("file")
        file: MultipartFile
    ): ApiResult<String> {
        val data = jwtService.verifyToken(authorization)
        val account = authService.readLoginUser(data?.sub!!)
        val extension = file.originalFilename?.split('.')?.last()
        val fileName = account.uid + "." + extension!!
        val uploadUrl = supabaseService.uploadFileWithSupabaseStorage(fileName, file.bytes)
        if(fileService.checkedUserHasProfileImage(account.member?.id!!)){

        }else{

        }

        val profileImage = fileService.saveFileProfileImage(uploadUrl, account.member!!)
        if (account.member == null) {
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "가입후 이용 가능한 서비스 입니다.")
        }
        println("profile $profileImage")
        service.updateMemberProfileImage(account.member?.id!!, profileImage)
        return ApiResult.success(profileImage.file.profileUrl!!)
    }

    @GetMapping("/recommend")
    override fun getRecommendUser(@RequestHeader("Authorization") authorization: String): ApiResult<List<Member>> {
        val jwtBody = jwtService.verifyToken(authorization)
        return ApiResult.success(service.getRecommendUser(jwtBody?.sub!!))
    }

}