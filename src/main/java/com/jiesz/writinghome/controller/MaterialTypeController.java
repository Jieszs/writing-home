package com.jiesz.writinghome.controller;

import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.entity.MaterialType;
import com.jiesz.writinghome.service.IMaterialTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 素材分类 前端控制器
 * </p>
 *
 * @author Jiesz
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/api")
@Validated
@Api(tags = "素材分类管理")
public class MaterialTypeController {
    @Resource
    private IMaterialTypeService iMaterialTypeService;

    @ApiOperation("添加素材分类")
    @PostMapping("/materialtypes")
    public Result<MaterialType> insert(
            @RequestBody @Validated MaterialType materialType
    ) {
        materialType.insert();
        return Result.success(materialType);
    }

    @ApiOperation("修改素材分类")
    @PutMapping("/updatematerialtypes/{typeId}")
    public Result update(
            @RequestBody @Validated MaterialType materialType

    ) {
        if (null == materialType.selectById()) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        materialType.updateById();
        return Result.success();
    }


    @ApiOperation("获取素材分类树")
    @GetMapping("/materialtypes/tree")
    public Result
            <List<MaterialType>> getTree(
            @RequestParam @ApiParam(value = "用户id", required = true) Integer userId
    ) {
        MaterialType condition = MaterialType.builder()
                .userId(userId)
                .build();
        List<MaterialType> tree = iMaterialTypeService.getTree(condition);
        return Result.success(tree);
    }

    @ApiOperation("获取素材分类详情")
    @GetMapping("/materialtypes/{typeId}")
    public Result<MaterialType> get(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer typeId
    ) {
        MaterialType condition = MaterialType.builder().typeId(typeId).build();
        MaterialType result = condition.selectById();
        if (result == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        return Result.success(result);
    }

    @ApiOperation("删除素材分类")
    @DeleteMapping("/materialtypes/{typeId}")
    public Result delete(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer typeId
    ) {
        MaterialType condition = MaterialType.builder().typeId(typeId).build();
        if (condition.selectById() == null) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        condition.deleteById();
        return Result.success("删除成功");
    }
}
