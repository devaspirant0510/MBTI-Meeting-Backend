package org.ngod.mbtisns.controller.member.dto

import org.ngod.mbtisns.data.entity.enum.Mbti

data class MemberDto(
    var nickName:String,
    var mbti:Mbti,
    var gender:Boolean
)
