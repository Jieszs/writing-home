package com.jiesz.writinghome.controller;

import com.jiesz.writinghome.common.TokenKey;
import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.entity.MaterialType;
import com.jiesz.writinghome.service.IMaterialTypeService;
import com.jiesz.writinghome.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.jiesz.writinghome.common.enums.ResultCode.DATA_ALREADY_EXISTED;

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
    @PostMapping("/materialTypes")
    public Result<MaterialType> insert(
            @RequestParam @ApiParam(value = "分类名称", required = true) String typeName,
            @RequestParam @ApiParam(value = "父亲id", required = true) Integer parentId
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        if (iMaterialTypeService.existTypeName(typeName, userId)) {
            return Result.fail(DATA_ALREADY_EXISTED.getCode(), "素材分类名称已存在");
        }
        MaterialType materialType = MaterialType.builder()
                .userId(userId)
                .typeName(typeName)
                .parentId(parentId)
                .orderId(iMaterialTypeService.getMaxOrderId(parentId, userId))
                .build();

        materialType.insert();
        return Result.success(materialType);
    }

    @ApiOperation("修改素材分类")
    @PutMapping("/materialTypes/{typeId}")
    public Result update(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer typeId,
            @RequestParam @ApiParam(value = "分类名称", required = true) String typeName
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        MaterialType materialType = iMaterialTypeService.getById(typeId);

        if (null == materialType) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        if (iMaterialTypeService.existTypeName(typeId, typeName, userId)) {
            return Result.fail(DATA_ALREADY_EXISTED.getCode(), "素材分类名称已存在");
        }
        materialType.updateById();
        return Result.success();
    }


    @ApiOperation("获取素材分类树")
    @GetMapping("/materialTypes/tree")
    public Result
            <List<MaterialType>> getTree(
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        MaterialType condition = MaterialType.builder()
                .userId(userId)
                .build();
        List<MaterialType> tree = iMaterialTypeService.getTree(condition);
        return Result.success(tree);
    }

    @ApiOperation("获取素材分类详情")
    @GetMapping("/materialTypes/{typeId}")
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
    @DeleteMapping("/materialTypes/{typeId}")
    public Result delete(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer typeId
    ) {
        MaterialType condition = iMaterialTypeService.getById(typeId);
        iMaterialTypeService.delete(condition);
        return Result.success("删除成功");
    }

    @ApiOperation("上移素材分类")
    @PutMapping("/materialTypes/{typeId}/up")
    public Result up(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer typeId
    ) {
        MaterialType type = iMaterialTypeService.getById(typeId);
        if (null == type) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        iMaterialTypeService.up(type);
        return Result.success();
    }

    @ApiOperation("下移素材分类")
    @PutMapping("/materialTypes/{typeId}/down")
    public Result down(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer typeId
    ) {
        MaterialType type = iMaterialTypeService.getById(typeId);
        if (null == type) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        iMaterialTypeService.down(type);
        return Result.success();
    }
}
