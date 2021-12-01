package com.jiesz.writinghome.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiesz.writinghome.common.TokenKey;
import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.entity.MaterialComment;
import com.jiesz.writinghome.service.IMaterialCommentService;
import com.jiesz.writinghome.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author Jiesz
 * @since 2021-12-01
 */
@RestController
@RequestMapping("/api")
@Validated
@Api(tags = "评论管理")
public class MaterialCommentController {
    @Resource
    private IMaterialCommentService iMaterialCommentService;

    @ApiOperation("添加评论")
    @PostMapping("/materialComment")
    public Result<MaterialComment> insert(
            @RequestParam @ApiParam(value = "素材id", required = true) Integer materialId,
            @RequestParam @ApiParam(value = "评论", required = true) String comment,
            @RequestParam @ApiParam(value = "接收用户id", required = true) Integer receiver,
            @RequestParam @ApiParam(value = "被评论的id,（可以素材，也可以是评论）", required = true) Integer commentedId,
            @RequestParam @ApiParam(value = "1-评论素材 2-回复评论", required = true) Integer type
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        MaterialComment materialComment = MaterialComment.builder()
                .comment(comment)
                .materialId(materialId)
                .userId(userId)
                .commentedId(commentedId)
                .receiver(receiver)
                .type(type)
                .build();
        return Result.success(materialComment);
    }

    @ApiOperation("修改评论")
    @PutMapping("/materialComment/{commentId}")
    public Result update(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer commentId,
            @RequestParam @ApiParam(value = "评论", required = true) String comment
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));

        MaterialComment materialComment = MaterialComment.builder()
                .comment(comment)
                .commentId(commentId)
                .build();
        MaterialComment one = iMaterialCommentService.getById(commentId);
        if (null == one || !one.getUserId().equals(userId)) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        materialComment.updateById();
        return Result.success();
    }

    @ApiOperation("获取评论列表")
    @GetMapping("/materialComment")
    public Result
            <Page<MaterialComment>> list(
            @RequestParam(required = false) @ApiParam(value = "素材id") Integer materialId,
            @RequestParam(required = false) @ApiParam(value = "评论") String comment,
            @RequestParam(required = false) @ApiParam(value = "用户id") Integer userId,
            @RequestParam(required = false) @ApiParam(value = "接收用户id") Integer receiver,
            @RequestParam(required = false) @ApiParam(value = "被评论的id,（可以素材，也可以是评论）") Integer commentedId,
            @RequestParam(required = false) @ApiParam(value = "1-评论素材 2-回复评论") Integer type,
            @RequestParam(defaultValue = "0") @ApiParam(value = "偏移量") Integer offset,
            @RequestParam(defaultValue = "10") @ApiParam(value = "限制") Integer limit
    ) {
        MaterialComment condition = MaterialComment.builder()
                .materialId(materialId)
                .comment(comment)
                .userId(userId)
                .receiver(receiver)
                .commentedId(commentedId)
                .type(type)
                .build();
        Integer total = iMaterialCommentService.count(condition);
        List<MaterialComment> list = new ArrayList<>();
        if (total > 0) {
            condition.setOffset(offset);
            condition.setLimit(limit);
            list = iMaterialCommentService.list(condition);
        }
        Page<MaterialComment> result = new Page<>();
        result.setTotal(total);
        result.setRecords(list);
        result.setCurrent(offset);
        result.setSize(limit);
        return Result.success(result);

    }

    @ApiOperation("获取评论详情")
    @GetMapping("/materialComment/{commentId}")
    public Result<MaterialComment> get(
            @PathVariable @ApiParam(value = "评论id", required = true) Integer commentId
    ) {
        MaterialComment condition = MaterialComment.builder().commentId(commentId).build();
        MaterialComment result = condition.selectById();
        if (result == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        return Result.success(result);
    }

    @ApiOperation("删除评论")
    @DeleteMapping("/materialComment/{commentId}")
    public Result delete(
            @PathVariable @ApiParam(value = "评论id", required = true) Integer commentId
    ) {
        MaterialComment condition = MaterialComment.builder().commentId(commentId).build();
        if (condition.selectById() == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        condition.deleteById();
        return Result.success("删除成功");
    }
}
