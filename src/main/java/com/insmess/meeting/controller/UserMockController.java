package com.insmess.meeting.controller;


import com.insmess.meeting.common.api.CommonResult;
import com.insmess.meeting.dto.UserInfo;
import com.insmess.meeting.entity.Room;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags = "UserMockController", description = "用户")
@RequestMapping("/userMock")
public class UserMockController {
    /**
     * 获取数据列表
     */
    @PostMapping("/userInfo")
    @ApiOperation("获取用户详情")
    public CommonResult userInfo(HttpServletRequest request){
        Map<String, UserInfo> map = new HashMap<>();
        map.put("jack", new UserInfo("jack", "杰克"));
        map.put("rose", new UserInfo("rose", "露西"));
        map.put("tom", new UserInfo("jack", "汤姆"));
        String token = request.getHeader("insmess-meeting-token");
        return CommonResult.success(map.get(token));
    }
}
