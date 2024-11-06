package org.ngod.mbtisns.controller.dm

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.ngod.mbtisns.controller.dm.dto.CreateRoomDto
import org.ngod.mbtisns.controller.dm.dto.SendDmDto
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Dm
import org.ngod.mbtisns.data.entity.DmRoom
import org.ngod.mbtisns.domain.service.DmService

@Tag(name = "Dm Api", description = "DM 관련 API")
interface DmSwagger {
    @Operation(summary = "채팅방 생성", description = "1대1 dm 으로 채팅방 생성")
    fun createRoom(data: CreateRoomDto): ApiResult<DmRoom>
    @Operation(summary = "채팅방 메시지 보내기", description = "상대에게 dm 메시지 전송")
    fun sendDm(roomId:Long,authorization:String,data:SendDmDto):ApiResult<Dm>
    @Operation(summary = "채팅방 dm 불러오기", description = "채팅방의 dm 전체 불러오기")
    fun readAllDmByRoomId(roomId: Long,authorization: String):ApiResult<List<Dm>>
    @Operation(summary = "채팅방 dm 불러오기", description = "채팅방의 dm 전체 불러오기")
    fun readAllDmByRoomIdPage(
        roomId: Long,
        page:Int,
        size:Int,
        authorization: String):ApiResult<List<Dm>>
    @Operation(summary = "유저의 채팅방 가져오기", description = "즉정 유저가 참가하고있는 채팅방 불러오기")
    fun readAllDmRoomByUserId(
        accountId:Long,
        authorization: String):ApiResult<List<DmRoom>>
    @Operation(summary = "채팅방 정보 가져오기", description = "특정 id 의 채팅방 정보 가져오기")
    fun readOneDmRoomById(
        roomId:Long,
        authorization: String
    ): ApiResult<DmRoom>

}