package org.ngod.mbtisns.data.projection

import org.ngod.mbtisns.data.entity.enum.Mbti

interface MemberProjection {
    val nickName:String?
    val gender:Boolean?
    val mbti:Mbti?
    val profileImage:ProfileImageProjection
}