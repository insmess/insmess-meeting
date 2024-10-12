package com.insmess.meeting.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insmess.meeting.common.api.CommonResult;
import com.insmess.meeting.common.constant.RoomConstant;
import com.insmess.meeting.entity.Room;
import com.insmess.meeting.service.RoomService;
import io.openvidu.java.client.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 会议室
 *
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
@Slf4j
@RestController
@Api(tags = "RoomController", description = "会议室")
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @Autowired
    private OpenVidu openvidu;

    private Map<String, Boolean> sessionRecordings = new ConcurrentHashMap<>();


    @SneakyThrows
    @GetMapping("/getRole")
    @ApiOperation("获取角色，host和member")
    public CommonResult<Object> getRole(String username, String sessionId){
        String role = roomService.getRole(sessionId, username);
        return CommonResult.success(role);
    }

    /**
     * 通过id获取
     */
    @PostMapping("/checkPassword")
    @ApiOperation("检查密码")
    public CommonResult<Room> checkPassword(String roomId, String password){
        try {
            roomService.checkRoomPassword(roomId, password);
            return CommonResult.success();
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 通过id获取
     */
    @GetMapping("/findById")
    @ApiOperation("通过id获取")
    public CommonResult<Room> findById(String id, Principal principal){
        try {
            Room room = roomService.getById(id);
            return CommonResult.success(room);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 根据roomId检查是否具有该会议的权限
     */
    @GetMapping("/checkRoomPermission")
    @ApiOperation("根据roomId检查是否具有该会议的权限")
    public CommonResult<Room> checkRoomPermission(String roomId, Principal principal){
        return CommonResult.success();
    }

    /**
     * 分页查询获取
     */
    @GetMapping("/findPublicRoomPage")
    @ApiOperation("分页查询获取所有公共会议室")
    public CommonResult<Page> findPublicRoomPage(
                                        @RequestParam(defaultValue = "1") Long pageNum,
                                        @RequestParam(defaultValue = "10") Long pageSize){
        Page page = roomService.pagePublicRoom(pageNum, pageSize);
        return CommonResult.success(page);
    }

    /**
     * 获取数据列表
     */
    @GetMapping("/findList")
    @ApiOperation("获取数据列表")
    public CommonResult<List<Room>> findList(Room room){

        List<Room> list = roomService.list(room);
        if(null == list){
            return CommonResult.failed("没有查询到值");
        }
        return CommonResult.success(list);
    }

    /**
     * 添加数据
     */
    @PostMapping("/save")
    @ApiOperation("保存数据")
    public CommonResult<Room> save(@RequestBody Room room){
        if(roomService.save(room)){
            return CommonResult.success(room);
        }
        return CommonResult.failed("保存或更新失败");
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult<Room> update(@RequestBody Room room){
        if(roomService.updateById(room)){
            return CommonResult.success(room);
        }
        return CommonResult.failed("修改失败");
    }


    /**
     *通过id删除
     */
    @PostMapping("/deleteById")
    @ApiOperation("通过id删除")
    public CommonResult<Object> deleteById(String id){

        if(roomService.removeById(id)){
            return CommonResult.success("删除成功");
        }else{
            return CommonResult.failed("删除失败");
        }
    }

    /**
     * 破坏连接（提出会议室）
     */
    @SneakyThrows
    @PostMapping("/disconnect")
    @ApiOperation("踢出会议")
    public CommonResult<Object> disconnect(String sessionId, String connectionId){
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null) {
            return CommonResult.failed("未找到连接");
        }
        session.forceDisconnect(connectionId);
        return CommonResult.success("踢出会议成功");
    }

    /**
     * 全体静音
     */
    /*@SneakyThrows
    @PostMapping("/muteByStreamId")
    @ApiOperation("踢出会议")
    public CommonResult<Object> muteAll(String sessionId, String streamId){
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null) {
            return CommonResult.failed("未找到连接");
        }
        List<Connection> connections = session.getConnections();
        connections.forEach(con -> {
            con.res
        });
    }*/

    @SneakyThrows
    @GetMapping("/testHeelo")
    @ApiOperation("testHeelo")
    public CommonResult<Object> testHeelo(String sessionId){
        Session session = openvidu.getActiveSession(sessionId);
        if (session == null) {
            return CommonResult.failed("未找到连接");
        }
        List<Connection> connections = session.getConnections();
        for (Connection con : connections) {
            Map<String, Object> params = new HashMap<>();
            params.put("data", "fs");
            session.updateConnection(con.getConnectionId(), ConnectionProperties.fromJson(params).build());
        }
        return CommonResult.success();
    }

    /**
     * @param params The Session properties
     * @return The Session ID
     */
    @PostMapping("/sessions")
    public CommonResult initializeSession(@RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        //校验是否存在会议以及会议密码
        String sessionId = String.valueOf(params.get("customSessionId"));
        String password = String.valueOf(params.get("password"));
        try {
            roomService.checkRoomPermission(sessionId, null, password);
            SessionProperties properties = SessionProperties.fromJson(params).build();
            Session session = openvidu.createSession(properties);
            return CommonResult.success(session.getSessionId());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * @param sessionId The Session in which to create the Connection
     * @param params    The Connection properties
     * @return The Token associated to the Connection
     */
    @PostMapping("/sessions/{sessionId}/connections")
    public CommonResult createConnection(@PathVariable("sessionId") String sessionId,
                                                   @RequestBody(required = false) Map<String, Object> params)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = openvidu.getActiveSession(sessionId);
        String password = String.valueOf(params.get("password"));
        if (session == null) {
            return CommonResult.failed();
        }
        try {
            roomService.checkRoomPermission(sessionId, null, password);
            ConnectionProperties properties = ConnectionProperties.fromJson(params).build();
            Connection connection = session.createConnection(properties);
            return CommonResult.success(connection.getToken());
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
    }


    /**
     * 开始录制视频
     * @param params
     * @return
     */
    @PostMapping(value = "/recording/start")
    public CommonResult startRecording(@RequestBody Map<String, Object> params) {
        String sessionId = (String) params.get("session");
        Recording.OutputMode outputMode = Recording.OutputMode.valueOf((String) params.get("outputMode"));
        boolean hasAudio = (boolean) params.get("hasAudio");
        boolean hasVideo = (boolean) params.get("hasVideo");

        RecordingProperties properties = new RecordingProperties.Builder().outputMode(outputMode).hasAudio(hasAudio)
                .hasVideo(hasVideo).build();

        System.out.println("Starting recording for session " + sessionId + " with properties {outputMode=" + outputMode
                + ", hasAudio=" + hasAudio + ", hasVideo=" + hasVideo + "}");

        try {
            Recording recording = openvidu.startRecording(sessionId, properties);
            this.sessionRecordings.put(sessionId, true);
            return CommonResult.success(recording);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 停止录制视频
     * @param params
     * @return
     */
    @PostMapping(value = "/recording/stop")
    public CommonResult stopRecording(@RequestBody Map<String, Object> params) {
        String recordingId = (String) params.get("recording");

        System.out.println("Stoping recording | {recordingId}=" + recordingId);

        try {
            Recording recording = openvidu.stopRecording(recordingId);
            this.sessionRecordings.remove(recording.getSessionId());
            return CommonResult.success(recording);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            return CommonResult.failed(e.getMessage());
        }
    }

    /**
     * 删除视频·
     * @param params
     * @return
     */
    @RequestMapping(value = "/recording/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRecording(@RequestBody Map<String, Object> params) {
        String recordingId = (String) params.get("recording");

        System.out.println("Deleting recording | {recordingId}=" + recordingId);

        try {
            openvidu.deleteRecording(recordingId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
