package com.jiesz.writinghome.controller;

import com.jiesz.writinghome.common.TokenKey;
import com.jiesz.writinghome.util.TokenUtil;
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
    @PutMapping("/users/self")
    public Result update(
            @RequestBody @Validated User user
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        user.setUserId(userId);
        if (null == user.selectById()) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        user.updateById();
        return Result.success();
    }


    @ApiOperation("获取用户信息详情")
    @GetMapping("/users/self")
    public Result<User> getSelf(
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        User condition = User.builder().userId(userId).build();
        User result = condition.selectById();
        if (result == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        return Result.success(result);
    }


}
