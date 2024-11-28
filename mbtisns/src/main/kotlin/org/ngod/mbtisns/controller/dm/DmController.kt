package org.ngod.mbtisns.controller.dm

import org.ngod.mbtisns.controller.dm.dto.CreateRoomDto
import org.ngod.mbtisns.controller.dm.dto.SendDmDto
import org.ngod.mbtisns.core.ApiResult
import org.ngod.mbtisns.data.entity.Dm
import org.ngod.mbtisns.data.entity.DmRoom
import org.ngod.mbtisns.data.projection.DmProjection
import org.ngod.mbtisns.data.projection.DmRoomProjection
import org.ngod.mbtisns.domain.service.DmService
import org.ngod.mbtisns.domain.service.JwtService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger

@RestController
@RequestMapping("/api/v1/dm")
class DmController(
    private val dmService: DmService,
    private val jwtService: JwtService
) : DmSwagger {

    @PostMapping("/create")
    override fun createRoom(@RequestBody data: CreateRoomDto): ApiResult<DmRoom> {
        return ApiResult.success(dmService.createRoom(data.list))
    }

    @PostMapping("/send/{roomId}")
    override fun sendDm(
        @PathVariable(name = "roomId")
        roomId: Long,
        @RequestHeader("Authorization")
        authorization: String,
        @RequestBody
        data: SendDmDto
    ): ApiResult<Dm> {
        jwtService.verifyToken(authorization)
        val dm = dmService.sendMessage(data)
        return ApiResult.success(dm)

    }

    @GetMapping("/{roomId}")
    override fun readAllDmByRoomId(
        @PathVariable(name = "roomId")
        roomId: Long,
        @RequestHeader("Authorization")
        authorization: String
    ): ApiResult<List<DmProjection>> {
        return ApiResult.success(dmService.findAllDmByRoomId(roomId))
    }

    @GetMapping("/{roomId}/paging")
    override fun readAllDmByRoomIdPage(
        @PathVariable(name = "roomId")
        roomId: Long,
        @RequestParam
        page: Int,
        @RequestParam
        size: Int,
        @RequestHeader("Authorization")
        authorization: String
    ): ApiResult<List<DmProjection>> {
        return ApiResult.success(dmService.findAllDmByRoomIdAtPage(roomId, page, size).toList())
    }

    @GetMapping("/room/account/{accountId}")
    override fun readAllDmRoomByUserId(
        @PathVariable(name = "accountId")
        accountId: Long,
        @RequestHeader("Authorization")
        authorization: String
    ): ApiResult<List<DmRoom>> {
        println("방 조회")
        return ApiResult.success(dmService.findAllDmRoomByUserId(accountId))
    }

    @GetMapping("/room/{roomId}")
    override fun readOneDmRoomById(
        @PathVariable(name = "roomId")
        roomId: Long,
        @RequestHeader("Authorization")
        authorization: String
    ):ApiResult<DmRoomProjection> {
        jwtService.verifyToken(authorization)
        val room =dmService.findRoomById(roomId)
        return ApiResult.success(room)
    }

}