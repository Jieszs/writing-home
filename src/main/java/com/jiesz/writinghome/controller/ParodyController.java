package com.jiesz.writinghome.controller;

import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.entity.Parody;
import com.jiesz.writinghome.service.IParodyService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 仿写 前端控制器
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/api")
@Validated
@Api(tags = "仿写管理")
public class ParodyController {
    @Resource
    private IParodyService iParodyService;

    @ApiOperation("添加仿写")
    @PostMapping("/parodys")
    public Result<Parody> insert(
            @RequestBody @Validated Parody parody
    ) {
        parody.insert();
        return Result.success(parody);
    }

    @ApiOperation("修改仿写")
    @PutMapping("/parodys/{parodyId}")
    public Result update(
            @RequestBody @Validated Parody parody

    ) {
        if (null == parody.selectById()) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        parody.updateById();
        return Result.success();
    }

    @ApiOperation("获取仿写列表")
    @GetMapping("/parodys")
    public Result
            <Page<Parody>> list(
            @RequestParam(required = false) @ApiParam(value = "内容") String content,
            @RequestParam(required = false) @ApiParam(value = "素材id") Integer materialId,
            @RequestParam(required = false) @ApiParam(value = "用户id") Integer userId,
            @RequestParam(defaultValue = "0") @ApiParam(value = "偏移量") Integer offset,
            @RequestParam(defaultValue = "10") @ApiParam(value = "限制") Integer limit
    ) {
        Parody condition = Parody.builder()
                .content(content)
                .materialId(materialId)
                .userId(userId)
                .build();
        Integer total = iParodyService.count(condition);
        List<Parody> list = new ArrayList<>();
        if (total > 0) {
            condition.setOffset(offset);
            condition.setLimit(limit);
            list = iParodyService.list(condition);
        }
        Page<Parody> result = new Page<>();
        result.setTotal(total);
        result.setRecords(list);
        result.setCurrent(offset);
        result.setSize(limit);
        return Result.success(result);

    }

    @ApiOperation("获取仿写详情")
    @GetMapping("/parodys/{parodyId}")
    public Result<Parody> get(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer parodyId
    ) {
        Parody condition = Parody.builder().parodyId(parodyId).build();
        Parody result = condition.selectById();
        if (result == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        return Result.success(result);
    }

    @ApiOperation("删除仿写")
    @DeleteMapping("/parodys/{parodyId}")
    public Result delete(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer parodyId
    ) {
        Parody condition = Parody.builder().parodyId(parodyId).build();
        if (condition.selectById() == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        condition.deleteById();
        return Result.success("删除成功");
    }
}
