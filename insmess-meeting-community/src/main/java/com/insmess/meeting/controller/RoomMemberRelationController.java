package com.insmess.meeting.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.insmess.meeting.common.api.CommonResult;
import com.insmess.meeting.entity.RoomMemberRelation;
import com.insmess.meeting.service.RoomMemberRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;


/**
 * 会议室成员关联
 *
 * @author xujq
 * @email ${email}
 * @date 2024-04-27 18:54:16
 */
@Slf4j
@RestController
@Api(tags = "RoomMemberRelationController", description = "会议室成员关联")
@RequestMapping("/roomMemberRelation")
public class RoomMemberRelationController {

    @Autowired
    private RoomMemberRelationService roomMemberRelationService;

    /**
     * 通过id获取
     */
    @GetMapping("/findById")
    @ApiOperation("通过id获取")
    public CommonResult<RoomMemberRelation> findById(String id){
        RoomMemberRelation roomMemberRelation = roomMemberRelationService.getById(id);
        if(null == roomMemberRelation){
            return CommonResult.failed("没有查询到值");
        }
        return CommonResult.success(roomMemberRelation);
    }

    /**
     * 分页查询获取
     */
    @GetMapping("/findPage")
    @ApiOperation("分页查询获取")
    public CommonResult<Page> findPage(RoomMemberRelation roomMemberRelation,
                                        @RequestParam(defaultValue = "1") Long pageNum,
                                        @RequestParam(defaultValue = "10") Long pageSize){
        Page page = roomMemberRelationService.page(roomMemberRelation, pageNum, pageSize);
        if(null == page){
            return CommonResult.failed("没有查询到值");
        }
        return CommonResult.success(page);
    }

    /**
     * 获取数据列表
     */
    @GetMapping("/findList")
    @ApiOperation("获取数据列表")
    public CommonResult<List<RoomMemberRelation>> findList(RoomMemberRelation roomMemberRelation){

        List<RoomMemberRelation> list = roomMemberRelationService.list(roomMemberRelation);
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
    public CommonResult<RoomMemberRelation> save(@RequestBody RoomMemberRelation roomMemberRelation){
        if(roomMemberRelationService.save(roomMemberRelation)){
            return CommonResult.success(roomMemberRelation);
        }
        return CommonResult.failed("保存或更新失败");
    }

    /**
     * 修改数据
     */
    @PostMapping("/update")
    @ApiOperation("修改数据")
    public CommonResult<RoomMemberRelation> update(@RequestBody RoomMemberRelation roomMemberRelation){
        if(roomMemberRelationService.updateById(roomMemberRelation)){
            return CommonResult.success(roomMemberRelation);
        }
        return CommonResult.failed("修改失败");
    }


    /**
     *通过id删除
     */
    @PostMapping("/deleteById")
    @ApiOperation("通过id删除")
    public CommonResult<Object> deleteById(String id){

        if(roomMemberRelationService.removeById(id)){
            return CommonResult.success("删除成功");
        }else{
            return CommonResult.failed("删除失败");
        }
    }

}
