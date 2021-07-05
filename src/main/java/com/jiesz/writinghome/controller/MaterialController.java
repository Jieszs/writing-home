package com.jiesz.writinghome.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.entity.Material;
import com.jiesz.writinghome.service.IMaterialService;
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
 * 素材 前端控制器
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/api")
@Validated
@Api(tags = "素材管理")
public class MaterialController {
    @Resource
    private IMaterialService iMaterialService;

    @ApiOperation("添加素材")
    @PostMapping("/materials")
    public Result<Material> insert(
            @RequestBody @Validated Material material
    ) {

        iMaterialService.insert(material);
        return Result.success(material);
    }

    @ApiOperation("修改素材")
    @PutMapping("/materials/{materialId}")
    public Result update(
            @RequestBody @Validated Material material

    ) {
        if (null == material.selectById()) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        material.updateById();
        return Result.success();
    }

    @ApiOperation("获取素材列表")
    @GetMapping("/materials")
    public Result<Page<Material>> list(
            @RequestParam(required = false) @ApiParam(value = "素材内容") String content,
            @RequestParam(required = false) @ApiParam(value = "用户id") Integer userId,
            @RequestParam(required = false) @ApiParam(value = "来源") String source,
            @RequestParam(defaultValue = "0") @ApiParam(value = "偏移量") Integer offset,
            @RequestParam(defaultValue = "10") @ApiParam(value = "限制") Integer limit
    ) {
        Material condition = Material.builder()
                .content(content)
                .userId(userId)
                .source(source)
                .build();
        Integer total = iMaterialService.count(condition);
        List<Material> list = new ArrayList<>();
        if (total > 0) {
            condition.setOffset(offset);
            condition.setLimit(limit);
            list = iMaterialService.list(condition);
        }
        Page<Material> result = new Page<>();
        result.setTotal(total);
        result.setRecords(list);
        result.setCurrent(offset);
        result.setSize(limit);
        return Result.success(result);

    }

    @ApiOperation("获取素材详情")
    @GetMapping("/materials/{materialId}")
    public Result<Material> get(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer materialId
    ) {
        Material condition = Material.builder().materialId(materialId).build();
        Material result = condition.selectById();
        if (result == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        return Result.success(result);
    }

    @ApiOperation("删除素材")
    @DeleteMapping("/materials/{materialId}")
    public Result delete(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer materialId
    ) {
        Material condition = Material.builder().materialId(materialId).build();
        if (condition.selectById() == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        condition.deleteById();
        return Result.success("删除成功");
    }
}
