package org.ngod.mbtisns.data.projection

interface FollowProjection {
    val follower:MemberProjection
    val following:MemberProjection
}