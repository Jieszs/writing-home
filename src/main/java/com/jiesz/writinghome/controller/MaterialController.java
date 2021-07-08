package com.jiesz.writinghome.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiesz.writinghome.common.TokenKey;
import com.jiesz.writinghome.common.bean.Result;
import com.jiesz.writinghome.common.enums.ResultCode;
import com.jiesz.writinghome.entity.Material;
import com.jiesz.writinghome.service.IMaterialService;
import com.jiesz.writinghome.service.IMaterialTypeService;
import com.jiesz.writinghome.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.CollectionUtils;
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

    @Resource
    private IMaterialTypeService materialTypeService;

    @ApiOperation("添加素材")
    @PostMapping("/materials")
    public Result<Material> insert(
            @RequestParam @ApiParam(value = "素材内容", required = true) String content,
            @RequestParam @ApiParam(value = "来源", required = true) String source,
            @RequestParam @ApiParam(value = "父级素材Id（0-本素材不是仿写）", required = true) Integer parentId,
            @RequestParam(required = false) @ApiParam(value = "分类id") List<Integer> typeIds
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        Material material = Material.builder()
                .content(content)
                .source(source)
                .parentId(parentId)
                .typeIds(typeIds)
                .build();
        material.setUserId(userId);
        if (!CollectionUtils.isEmpty(typeIds) && !materialTypeService.validateTypeIds(material.getTypeIds(), userId)) {
            return Result.fail(ResultCode.PARAM_VALID_ERROR.getCode(), "素材分类不合法");
        }
        iMaterialService.insert(material);
        return Result.success(material);
    }

    @ApiOperation("修改素材")
    @PutMapping("/materials/{materialId}")
    public Result update(
            @PathVariable @ApiParam(value = "主键id", required = true) Integer materialId,
            @RequestParam(required = false) @ApiParam(value = "素材内容") String content,
            @RequestParam(required = false) @ApiParam(value = "来源") String source,
            @RequestParam(required = false) @ApiParam(value = "分类id") List<Integer> typeIds
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));
        if (!CollectionUtils.isEmpty(typeIds) && !materialTypeService.validateTypeIds(typeIds, userId)) {
            return Result.fail(ResultCode.PARAM_VALID_ERROR.getCode(), "素材分类不合法");
        }
        if (null == iMaterialService.getById(materialId)) {
            return Result.fail(ResultCode.DATA_NOT_FOUND);
        }
        Material material = Material.builder()
                .content(content)
                .source(source)
                .typeIds(typeIds)
                .materialId(materialId)
                .build();
        iMaterialService.update(material);
        return Result.success();
    }

    @ApiOperation("获取素材列表")
    @GetMapping("/materials")
    public Result<Page<Material>> list(
            @RequestParam(required = false) @ApiParam(value = "素材内容模糊匹配") String content,
            @RequestParam(required = false) @ApiParam(value = "来源") String source,
            @RequestParam(required = false) @ApiParam(value = "父级素材Id（0-本素材不是仿写）") Integer parentId,
            @RequestParam(required = false) @ApiParam(value = "类别") List<Integer> typeIds,
            @RequestParam(defaultValue = "0") @ApiParam(value = "偏移量") Integer offset,
            @RequestParam(defaultValue = "10") @ApiParam(value = "限制") Integer limit
    ) {
        Integer userId = Integer.parseInt(TokenUtil.getFromToken(TokenKey.USER_ID));

        Material condition = Material.builder()
                .content(content)
                .userId(userId)
                .parentId(parentId)
                .source(source)
                .typeIds(typeIds)
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
        Material result = iMaterialService.getDetail(materialId);
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
        List<Material> materials = iMaterialService.list(new LambdaQueryWrapper<Material>().eq(Material::getParentId, materialId));
        if (!CollectionUtils.isEmpty(materials)) {
            return Result.fail(ResultCode.PARAM_VALID_ERROR.getCode(), "存在仿写素材，不允许删除");
        }
        condition.deleteById();
        return Result.success("删除成功");
    }
}
