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
import com.jiesz.writinghome.entity.User;
import com.jiesz.writinghome.service.IUserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-06
 */
@RestController
@RequestMapping("/api")
@Validated
@Api(tags = "用户信息管理")
public class UserController {
    @Resource
    private IUserService iUserService;

    @ApiOperation("添加用户信息")
    @PostMapping("/users")
    public Result<User> insert(
            @RequestBody @Validated User user
    ) {
        user.insert();
        return Result.success(user);
    }

    @ApiOperation("修改用户信息")
    @PutMapping("/users/{userId}")
    public Result update(
            @RequestBody @Validated User user

    ) {
        if (null == user.selectById()) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        user.updateById();
        return Result.success();
    }

    @ApiOperation("获取用户信息列表")
    @GetMapping("/users")
    public Result
            <Page<User>> list(
            @RequestParam(defaultValue = "0") @ApiParam(value = "偏移量") Integer offset,
            @RequestParam(defaultValue = "10") @ApiParam(value = "限制") Integer limit
    ) {
        User condition = User.builder()
                .build();
        Integer total = iUserService.count(condition);
        List<User> list = new ArrayList<>();
        if (total > 0) {
            condition.setOffset(offset);
            condition.setLimit(limit);
            list = iUserService.list(condition);
        }
        Page<User> result = new Page<>();
        result.setTotal(total);
        result.setRecords(list);
        result.setCurrent(offset);
        result.setSize(limit);
        return Result.success(result);

    }

    @ApiOperation("获取用户信息详情")
    @GetMapping("/users/{userId}")
    public Result<User> get(
            @PathVariable @ApiParam(value = "用户id", required = true) Integer userId
    ) {
        User condition = User.builder().userId(userId).build();
        User result = condition.selectById();
        if (result == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        return Result.success(result);
    }

    @ApiOperation("删除用户信息")
    @DeleteMapping("/users/{userId}")
    public Result delete(
            @PathVariable @ApiParam(value = "用户id", required = true) Integer userId
    ) {
        User condition = User.builder().userId(userId).build();
        if (condition.selectById() == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        condition.deleteById();
        return Result.success("删除成功");
    }
}
